package com.hieuwu.trendingmovies.data.local.movie.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieDetailsEntity
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun pagingSource(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM movies")
    suspend fun size(): Int

    @Query("SELECT *FROM movie_details where id = :id")
    suspend fun getMovieDetails(id: Int): MovieDetailsEntity?

    @Upsert
    suspend fun upsert(movieDetails: MovieDetailsEntity)
}