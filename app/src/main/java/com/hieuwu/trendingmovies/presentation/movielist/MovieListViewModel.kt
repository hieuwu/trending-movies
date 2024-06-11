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
    val moviePagingFlow = movieRepository.getTrendingMovies().cachedIn(viewModelScope)

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun onSearchQueryChange(query: String) {
        _query.value = query
    }

    fun onSearchMovie() {
        if (_query.value.isEmpty()) {

        } else {
            searchMovie()
        }
    }

    private fun searchMovie() {
        viewModelScope.launch {
            val movieList = movieRepository.searchMovie(_query.value)
            _movies.emit(movieList)
        }
    }
}