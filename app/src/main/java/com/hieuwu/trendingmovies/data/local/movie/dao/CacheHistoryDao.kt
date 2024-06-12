package com.hieuwu.trendingmovies.data.local.movie.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hieuwu.trendingmovies.data.local.movie.entity.CacheHistoryEntity

@Dao
interface CacheHistoryDao {
    @Query("SELECT last_updated FROM cache_history WHERE id = (SELECT MAX(ID) FROM cache_history)")
    suspend fun getLatestUpdate(): Long?

    @Query("DELETE FROM cache_history")
    suspend fun clearAll()

    @Insert
    suspend fun insert(cacheHistoryEntity: CacheHistoryEntity)
}