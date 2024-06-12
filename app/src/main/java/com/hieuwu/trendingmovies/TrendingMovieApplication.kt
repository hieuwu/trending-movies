package com.hieuwu.trendingmovies

import android.app.Application
import com.hieuwu.trendingmovies.data.repository.CacheHistoryRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.Forest.plant
import javax.inject.Inject


@HiltAndroidApp
class TrendingMovieApplication : Application() {

    @Inject
    lateinit var cacheHistoryRepository: CacheHistoryRepository

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            cacheHistoryRepository.invalidateCache()
        }
    }
}