package com.hieuwu.trendingmovies.data.network.movie.dto

import com.squareup.moshi.Json

data class MovieDetailsDto(
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "homepage") val homepage: String? = null,
    @Json(name = "id") val id: Int,
    @Json(name = "overview") val overview: String? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "vote_average") val voteAverage: Double? = null,
)