package com.hieuwu.trendingmovies.data.network.movie.impl

import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDto
import com.hieuwu.trendingmovies.data.network.movie.retrofit.MovieRetrofitService
import javax.inject.Inject

internal class MovieRetrofitServiceImpl @Inject constructor(
    private val movieRetrofitService: MovieRetrofitService
) : MovieService {
    override suspend fun getMovieList(): List<MovieDto> {
        val result = movieRetrofitService.getMovieList()
        return result.results
    }
}