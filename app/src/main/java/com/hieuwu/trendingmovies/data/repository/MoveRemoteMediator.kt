package com.hieuwu.trendingmovies.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hieuwu.trendingmovies.data.local.movie.TrendingMovieDatabase
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity
import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.repository.impl.toEntity
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class MoveRemoteMediator @Inject constructor(
    private val movieDatabase: TrendingMovieDatabase,
    private val movieService: MovieService
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        Timber.d("Last item index-0 Next page-1")
                        1
                    } else {
                        val lastIndex = movieDatabase.movieDao().size()
                        if (lastIndex % 20 == 0) {
                            val nextPage = (lastIndex / state.config.pageSize) + 1
                            Timber.d("Last item index-$lastIndex Next page-$nextPage")
                            nextPage
                        } else {
                            MediatorResult.Success(endOfPaginationReached = true)
                            -1
                        }
                    }
                }
            }
            val movies = movieService.getMovieList(loadKey)
            movieDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieDatabase.movieDao().clearAll()
                }
                val movieEntities = movies.map {
                    it.toEntity()
                }
                movieDatabase.movieDao().upsertAll(movieEntities)
            }
            MediatorResult.Success(endOfPaginationReached = movies.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}