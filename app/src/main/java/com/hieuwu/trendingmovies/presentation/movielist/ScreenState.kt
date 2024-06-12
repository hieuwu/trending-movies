package com.hieuwu.trendingmovies.presentation.movielist

sealed interface ScreenState {
    data object Trending: ScreenState
    data object Search: ScreenState
}