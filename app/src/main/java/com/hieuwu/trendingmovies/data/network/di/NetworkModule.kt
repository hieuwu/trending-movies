package com.hieuwu.trendingmovies.data.network.di

import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.network.movie.impl.MovieRetrofitServiceImpl
import com.hieuwu.trendingmovies.data.network.movie.retrofit.MovieRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/trending/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieRetrofitService(retrofit: Retrofit): MovieRetrofitService {
        return retrofit
            .create(MovieRetrofitService::class.java)
    }

    @Provides
    fun provideRewardService(movieRetrofitService: MovieRetrofitService): MovieService {
        return MovieRetrofitServiceImpl(movieRetrofitService)
    }
}