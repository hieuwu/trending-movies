package com.hieuwu.trendingmovies.data.repository.impl

import androidx.room.withTransaction
import com.hieuwu.trendingmovies.data.local.movie.TrendingMovieDatabase
import com.hieuwu.trendingmovies.data.local.movie.entity.CacheHistoryEntity
import com.hieuwu.trendingmovies.data.repository.CacheHistoryRepository
import com.hieuwu.trendingmovies.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class CacheHistoryRepositoryImpl @Inject constructor(
    private val movieDatabase: TrendingMovieDatabase,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : CacheHistoryRepository {
    override suspend fun invalidateCache() {
        withContext(ioDispatcher) {
            val lastUpdated = movieDatabase.cacheHistoryDao().getLatestUpdate()
            if (lastUpdated != null) {
                val current = Date().time
                val diff = current - lastUpdated
                val diffDays: Int = (diff / (1000 * 60 * 60 * 24) % 365).toInt()
                if (diffDays >= 1) {
                    with(movieDatabase) {
                        withTransaction {
                            cacheHistoryDao().clearAll()
                            movieDao().clearAll()
                            movieDao().clearAllMovieDetails()
                        }
                    }
                }
            } else {
                val lastUpdate = Date().time
                movieDatabase.cacheHistoryDao().insert(CacheHistoryEntity(lastUpdated = lastUpdate))
            }
        }
    }
}