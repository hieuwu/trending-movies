package com.hieuwu.trendingmovies.data.network.movie.retrofit

import com.hieuwu.trendingmovies.data.network.movie.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRetrofitService {

    @GET("/trending/movie")
    suspend fun getMovieList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): MovieListDto

    @GET("/search/movie")
    suspend fun searchMovieDefault(
        @Query("query") query: String,
    ): MovieListDto

}