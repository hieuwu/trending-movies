package com.hieuwu.trendingmovies.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity
import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.repository.MovieRepository
import com.hieuwu.trendingmovies.di.IoDispatcher
import com.hieuwu.trendingmovies.domain.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val pager: Pager<Int, MovieEntity>,
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
            val movies = movieService.searchMovie(query = query).map {
                it.toDomainModel()
            }
            return@withContext movies
        }
}