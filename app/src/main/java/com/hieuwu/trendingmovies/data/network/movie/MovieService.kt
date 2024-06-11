package com.hieuwu.trendingmovies.data.network.movie

import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDto

interface MovieService {
    suspend fun getMovieList(page: Int): List<MovieDto>
    suspend fun searchMovie(query: String): List<MovieDto>

}