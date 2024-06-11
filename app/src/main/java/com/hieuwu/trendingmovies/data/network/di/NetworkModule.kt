package com.hieuwu.trendingmovies.data.network.di

import com.hieuwu.trendingmovies.BuildConfig
import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.network.movie.impl.MovieRetrofitServiceImpl
import com.hieuwu.trendingmovies.data.network.movie.retrofit.MovieRetrofitService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOKhttpClient(): OkHttpClient {
        val okhttpClientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Timber.tag("NetworkLogger").d(message)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
        okhttpClientBuilder.addNetworkInterceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val decoratedRequest = originalRequest.newBuilder()
                .url(url)
                .build()
            chain.proceed(decoratedRequest)
        }
            .addNetworkInterceptor(loggingInterceptor)
        return okhttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.themoviedb.org/3/")
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