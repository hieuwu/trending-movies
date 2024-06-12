package com.hieuwu.trendingmovies.data.repository

import androidx.paging.PagingData
import com.hieuwu.trendingmovies.domain.model.Movie
import com.hieuwu.trendingmovies.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTrendingMovies(): Flow<PagingData<Movie>>
    suspend fun searchMovie(query: String): List<Movie>
    suspend fun getMovieDetails(movieId: Int): MovieDetails?
}