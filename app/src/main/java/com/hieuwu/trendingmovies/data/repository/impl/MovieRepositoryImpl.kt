package com.hieuwu.trendingmovies.data.repository.impl

import com.hieuwu.trendingmovies.data.local.movie.dao.MovieDao
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity
import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDto
import com.hieuwu.trendingmovies.data.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
) : MovieRepository {
    override suspend fun getTrendingMovies() {
        val networkMovies = movieService.getMovieList(1)

    }

    private fun mapDtoToEntity(movieDto: MovieDto): MovieEntity {
        return MovieEntity(
            id = movieDto.id,
            posterPath = movieDto.posterPath,
            title = movieDto.title,
            voteAverage = movieDto.voteAverage,
        )
    }
}