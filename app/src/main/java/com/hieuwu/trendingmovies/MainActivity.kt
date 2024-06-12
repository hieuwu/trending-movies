package com.hieuwu.trendingmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hieuwu.trendingmovies.presentation.moviedetails.MovieDetailsScreen
import com.hieuwu.trendingmovies.presentation.movielist.MovieListScreen
import com.hieuwu.trendingmovies.presentation.navigation.MovieDetailsDestination
import com.hieuwu.trendingmovies.presentation.navigation.MovieListDestination
import com.hieuwu.trendingmovies.ui.theme.TrendingMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrendingMoviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SharedTransitionLayout {
                        NavHost(
                            navController = navController,
                            startDestination = MovieListDestination.route
                        ) {
                            composable(MovieListDestination.route) {
                                MovieListScreen(
                                    navController = navController,
                                    animatedVisibilityScope = this
                                )
                            }

                            composable(
                                route = "${MovieDetailsDestination.route}/{${MovieDetailsDestination.movieId}}",
                                arguments = MovieDetailsDestination.arguments
                            ) { navBackStackEntry ->
                                val movieId =
                                    navBackStackEntry.arguments?.getInt(MovieDetailsDestination.movieId) ?: -1
                                MovieDetailsScreen(movieId = movieId, navController = navController,
                                    animatedVisibilityScope = this

                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
