package com.hieuwu.trendingmovies.data.local.di

import android.content.Context
import androidx.room.Room
import com.hieuwu.trendingmovies.data.local.movie.TrendingMovieDatabase
import com.hieuwu.trendingmovies.data.local.movie.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TrendingMovieDatabase {
        return Room.databaseBuilder(
            appContext,
            TrendingMovieDatabase::class.java,
            "movies.db"
        )
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMovieDao(database: TrendingMovieDatabase): MovieDao {
        return database.movieDao()
    }

}