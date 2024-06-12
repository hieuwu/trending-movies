package com.hieuwu.trendingmovies.data.network.movie.retrofit

import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDetailsDto
import com.hieuwu.trendingmovies.data.network.movie.dto.MovieListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRetrofitService {
    @GET("trending/movie/day")
    suspend fun getMovieList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<MovieListDto>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("include_adult") includeAdult: Boolean = false,
    ): Response<MovieListDto>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
    ): Response<MovieDetailsDto>
}