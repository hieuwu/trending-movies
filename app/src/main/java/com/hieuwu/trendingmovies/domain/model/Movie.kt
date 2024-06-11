package com.hieuwu.trendingmovies.domain.model

data class Movie(
    val id: Int,
    val posterPath: String? = null,
    val title: String,
    val voteAverage: Double? = null,
    val releaseDate: String,
)