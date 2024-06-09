package com.hieuwu.trendingmovies.data.network.movie

import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDto

interface MovieService {

    suspend fun getMovieList(): List<MovieDto>
}