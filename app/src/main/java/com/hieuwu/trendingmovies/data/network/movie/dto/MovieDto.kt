package com.hieuwu.trendingmovies.data.network.movie.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
)