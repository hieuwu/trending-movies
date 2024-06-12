package com.hieuwu.trendingmovies.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object MovieListDestination : Destination {
    override val route = "movie_list"
}

object MovieDetailsDestination : Destination {
    override val route = "movie_details"
    const val movieId = "movie_id"

    val arguments = listOf(navArgument(name = movieId) {
        type = NavType.IntType
    })

    fun createRouteWithParam(id: Int) = "$route/${id}"
}