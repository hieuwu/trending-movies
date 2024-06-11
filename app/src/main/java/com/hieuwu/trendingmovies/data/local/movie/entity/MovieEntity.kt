package com.hieuwu.trendingmovies.data.local.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @ColumnInfo("id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo("poster_path")
    val posterPath: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("vote_average")
    val voteAverage: Double,
    @ColumnInfo("release_date")
    val releaseDate: String? = null,
)