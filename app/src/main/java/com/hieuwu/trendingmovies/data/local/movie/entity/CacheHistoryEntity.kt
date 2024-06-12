package com.hieuwu.trendingmovies.data.local.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache_history")
data class CacheHistoryEntity(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("last_updated")
    val lastUpdated: Long,
)