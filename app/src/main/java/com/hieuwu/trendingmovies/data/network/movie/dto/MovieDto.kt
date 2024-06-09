package com.hieuwu.trendingmovies.data.network.movie.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
)