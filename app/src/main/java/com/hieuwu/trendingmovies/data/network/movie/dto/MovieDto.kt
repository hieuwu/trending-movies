package com.hieuwu.trendingmovies.data.network.movie.dto

import com.squareup.moshi.Json

data class MovieDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "backdrop_path")
    val backdropPath: String? = null,
    @Json(name = "title")
    val title: String,
    @Json(name = "vote_average")
    val voteAverage: Double = 0.0,
    @Json(name = "release_date")
    val releaseDate: String,
)