package com.hieuwu.trendingmovies.di

import com.hieuwu.trendingmovies.data.repository.MovieRepository
import com.hieuwu.trendingmovies.data.repository.impl.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

}