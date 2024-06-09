package com.hieuwu.trendingmovies.data.network.movie.retrofit

import com.hieuwu.trendingmovies.data.network.movie.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRetrofitService {

    @GET("/movie")
    suspend fun getMovieList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): MovieListDto

}