package com.hieuwu.trendingmovies.data.network.movie.dto

import com.squareup.moshi.Json

data class MovieDto(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "poster_path")
    val posterPath: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "vote_average")
    val voteAverage: Double? = null,
)