package com.hieuwu.trendingmovies.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity
import com.hieuwu.trendingmovies.data.repository.impl.toDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    pager: Pager<Int, MovieEntity>
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    fun onSearchQueryChange(query: String) {
        _query.value = query
    }

    val moviePagingFlow = pager.flow
        .map { pagingData ->
            pagingData.map {
                it.toDomainModel()
            }
        }.cachedIn(viewModelScope)
}