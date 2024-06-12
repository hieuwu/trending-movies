package com.hieuwu.trendingmovies.data.repository

interface CacheHistoryRepository {
    suspend fun invalidateCache()
}