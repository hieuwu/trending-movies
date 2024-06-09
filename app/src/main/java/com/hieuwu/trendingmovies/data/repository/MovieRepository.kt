package com.hieuwu.trendingmovies.data.repository

interface MovieRepository {
   suspend fun getTrendingMovies()
}