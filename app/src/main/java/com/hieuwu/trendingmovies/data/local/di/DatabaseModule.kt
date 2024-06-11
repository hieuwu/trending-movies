package com.hieuwu.trendingmovies.data.local.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.hieuwu.trendingmovies.data.local.movie.TrendingMovieDatabase
import com.hieuwu.trendingmovies.data.local.movie.dao.MovieDao
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity
import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.repository.MoveRemoteMediator
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

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMoviePager(
        movieDatabase: TrendingMovieDatabase,
        movieApiService: MovieService
    ): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MoveRemoteMediator(
                movieDatabase,
                movieApiService
            ),
            pagingSourceFactory = {
                movieDatabase.movieDao().pagingSource()
            }
        )
    }
}