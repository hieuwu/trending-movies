package com.hieuwu.trendingmovies.data.network.movie.retrofit

import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDto
import com.hieuwu.trendingmovies.data.network.movie.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface MovieRetrofitService {
    @GET("/trending/movie")
    suspend fun getMovieList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<List<MovieDto>>

    @GET("/search/movie")
    suspend fun searchMovieDefault(
        @Query("query") query: String,
    ): Response<MovieListDto>
}