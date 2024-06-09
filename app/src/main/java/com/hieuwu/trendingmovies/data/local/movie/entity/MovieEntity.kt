package com.hieuwu.trendingmovies.data.local.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class MovieEntity(
    @ColumnInfo("id")
    val id: Int? = null,
    @ColumnInfo("poster_path")
    val posterPath: String? = null,
    @ColumnInfo("title")
    val title: String? = null,
    @ColumnInfo("vote_average")
    val voteAverage: Double? = null,
)