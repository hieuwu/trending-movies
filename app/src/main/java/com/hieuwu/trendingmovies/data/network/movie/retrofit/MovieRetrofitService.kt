package com.hieuwu.trendingmovies.data.network.movie.retrofit

import com.hieuwu.trendingmovies.data.network.movie.dto.MovieListDto
import retrofit2.http.GET

interface MovieRetrofitService {

    @GET("/movie")
    suspend fun getMovieList(): MovieListDto
}