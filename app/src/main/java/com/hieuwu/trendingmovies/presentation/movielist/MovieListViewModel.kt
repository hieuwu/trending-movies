package com.hieuwu.trendingmovies.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hieuwu.trendingmovies.data.repository.MovieRepository
import com.hieuwu.trendingmovies.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val trendingMovies = movieRepository.getTrendingMovies().cachedIn(viewModelScope)

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Trending)
    val screenState: StateFlow<ScreenState> = _screenState

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _searchedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val searchedMovie: StateFlow<List<Movie>> = _searchedMovies

    fun onSearchQueryChange(query: String) {
        _query.value = query
    }

    fun onSearchMovie() {
        if (_query.value.isEmpty()) {
            _searchedMovies.value = emptyList()
            _screenState.value = ScreenState.Trending
        } else {
            searchMovie()
            _screenState.value = ScreenState.Search
        }
    }

    private fun searchMovie() {
        viewModelScope.launch {
            val movieList = movieRepository.searchMovie(_query.value)
            _searchedMovies.emit(movieList)
        }
    }
}