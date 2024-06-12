package com.hieuwu.trendingmovies.data.local.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @ColumnInfo("id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo("backdrop_path")
    val backdropPath: String? = null,
    @ColumnInfo("homepage")
    val homepage: String? = null,
    @ColumnInfo("overview")
    val overview: String? = null,
    @ColumnInfo("poster_path")
    val posterPath: String? = null,
    @ColumnInfo("release_date")
    val releaseDate: String? = null,
    @ColumnInfo("title")
    val title: String? = null,
    @ColumnInfo("vote_average")
    val voteAverage: Double? = null,
)