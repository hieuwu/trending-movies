package com.hieuwu.trendingmovies.data.repository.di

import com.hieuwu.trendingmovies.data.network.movie.impl.MovieRetrofitServiceImpl
import com.hieuwu.trendingmovies.data.network.movie.retrofit.MovieRetrofitService
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