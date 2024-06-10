package com.hieuwu.trendingmovies.data.network.di

import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.network.movie.impl.MovieRetrofitServiceImpl
import com.hieuwu.trendingmovies.data.network.movie.retrofit.MovieRetrofitService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val moshi: Moshi = Moshi.Builder().build()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieRetrofitService(retrofit: Retrofit): MovieRetrofitService {
        return retrofit
            .create(MovieRetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieService(movieRetrofitService: MovieRetrofitService): MovieService {
        return MovieRetrofitServiceImpl(movieRetrofitService)
    }
}