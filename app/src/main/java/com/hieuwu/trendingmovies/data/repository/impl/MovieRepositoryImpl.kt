package com.hieuwu.trendingmovies.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import com.hieuwu.trendingmovies.data.local.movie.TrendingMovieDatabase
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity
import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.repository.MovieRepository
import com.hieuwu.trendingmovies.di.IoDispatcher
import com.hieuwu.trendingmovies.domain.model.Movie
import com.hieuwu.trendingmovies.domain.model.MovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val pager: Pager<Int, MovieEntity>,
    private val movieDatabase: TrendingMovieDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : MovieRepository {
    override fun getTrendingMovies(): Flow<PagingData<Movie>> {
        return pager.flow
            .map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }
    }

    override suspend fun searchMovie(query: String): List<Movie> =
        withContext(ioDispatcher) {
            try {
                val movies = movieService.searchMovie(query = query).map {
                    it.toDomainModel()
                }
                return@withContext movies
            } catch (e: Exception) {
                Timber.d(e)
                return@withContext emptyList()
            }
        }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails? =
        withContext(ioDispatcher) {
            try {
                val localMovieDetails = movieDatabase.movieDao().getMovieDetails(movieId)
                if (localMovieDetails == null) {
                    movieDatabase.withTransaction {
                        movieService.getMovieDetails(movieId)?.let {
                            movieDatabase.movieDao().upsert(it.toEntity())
                        }
                    }
                    return@withContext movieDatabase.movieDao().getMovieDetails(movieId)
                        ?.toDomainModel()
                }
                return@withContext localMovieDetails.toDomainModel()
            } catch (e: Exception) {
                Timber.d(e)
                return@withContext null
            }
        }
}