package com.hieuwu.trendingmovies.data.network.movie.dto

import com.squareup.moshi.Json


data class MovieListDto(
    @Json(name="page")
    val page: Int? = null,
    @Json(name="results")
    val results: ArrayList<MovieDto> = arrayListOf(),
    @Json(name="total_pages")
    val totalPages: Int? = null,
    @Json(name="total_results")
    val totalResults: Int? = null,
)
