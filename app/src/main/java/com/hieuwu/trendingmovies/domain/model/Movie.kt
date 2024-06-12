package com.hieuwu.trendingmovies.domain.model

data class Movie(
    val id: Int,
    val backdropPath: String?,
    val title: String,
    val voteAverage: Double?,
    val releaseDate: String,
)