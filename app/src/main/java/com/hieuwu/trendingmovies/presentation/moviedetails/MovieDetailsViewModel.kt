package com.hieuwu.trendingmovies.presentation.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.trendingmovies.data.repository.MovieRepository
import com.hieuwu.trendingmovies.domain.model.MovieDetails
import com.hieuwu.trendingmovies.presentation.navigation.MovieDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> = _movieDetails

    init {
        val movieId = savedStateHandle.get<Int>(MovieDetailsDestination.movieId) ?: -1
        getMovieDetails(movieId)
    }

    private fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            val movie = movieRepository.getMovieDetails(id)
            _movieDetails.value = movie
        }
    }
}