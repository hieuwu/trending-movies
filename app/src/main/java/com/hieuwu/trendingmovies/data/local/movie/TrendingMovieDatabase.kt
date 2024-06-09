package com.hieuwu.trendingmovies.data.local.movie

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hieuwu.trendingmovies.data.local.movie.dao.MovieDao
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity


@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class TrendingMovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
