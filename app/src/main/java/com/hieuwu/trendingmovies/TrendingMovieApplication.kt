package com.hieuwu.trendingmovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class TrendingMovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        plant(Timber.DebugTree())
        Timber.tag("NetworkLogger").d("app started")
    }
}