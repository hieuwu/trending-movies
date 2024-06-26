package com.hieuwu.trendingmovies.data.network.movie.impl

import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDetailsDto
import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDto
import com.hieuwu.trendingmovies.data.network.movie.retrofit.MovieRetrofitService
import javax.inject.Inject

internal class MovieRetrofitServiceImpl @Inject constructor(
    private val movieRetrofitService: MovieRetrofitService
) : MovieService {
    override suspend fun getMovieList(page: Int): List<MovieDto> {
        return movieRetrofitService.getMovieList(page = page).body()?.results ?: emptyList()
    }

    override suspend fun searchMovie(query: String): List<MovieDto> {
        return movieRetrofitService.searchMovie(query = query).body()?.results ?: emptyList()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDto? {
        return movieRetrofitService.getMovieDetails(movieId = movieId).body()
    }
}