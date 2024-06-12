package com.hieuwu.trendingmovies.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hieuwu.trendingmovies.presentation.moviedetails.MovieDetailsScreen
import com.hieuwu.trendingmovies.presentation.movielist.MovieListScreen

fun NavGraphBuilder.navRegistration(navController: NavController) {
    composable(MovieListDestination.route) {
        MovieListScreen(
            navController = navController,
        )
    }

    composable(
        route = "${MovieDetailsDestination.route}/{${MovieDetailsDestination.movieId}}",
        arguments = MovieDetailsDestination.arguments
    ) { navBackStackEntry ->
        val movieId =
            navBackStackEntry.arguments?.getInt(MovieDetailsDestination.movieId) ?: -1
        MovieDetailsScreen(movieId = movieId, navController = navController)
    }
}