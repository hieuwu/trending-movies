package com.hieuwu.trendingmovies.presentation.movielist

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.hieuwu.trendingmovies.R
import com.hieuwu.trendingmovies.domain.model.Movie
import com.hieuwu.trendingmovies.presentation.movielist.composable.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = stringResource(R.string.trending_movies),
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                },
            )
        }
    ) { paddingValues ->
        val movies: LazyPagingItems<Movie> = viewModel.moviePagingFlow.collectAsLazyPagingItems()
        val context = LocalContext.current
        LaunchedEffect(key1 = movies.loadState) {
            if (movies.loadState.refresh is LoadState.Error) {
                Toast.makeText(
                    context,
                    "Error: " + (movies.loadState.refresh as LoadState.Error).error.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            val searchQuery = viewModel.query.collectAsState().value
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                label = {
                    Text(
                        text = stringResource(R.string.search_for_movies),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                maxLines = 1,
                shape = RoundedCornerShape(32),
                modifier = modifier
                    .fillMaxWidth(),
                value = searchQuery,
                onValueChange = {
                    viewModel.onSearchQueryChange(it)
                },
                keyboardActions = KeyboardActions(
                    onSearch = { viewModel.onSearchMovie() }
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = modifier
                            .clickable {
                                viewModel.onSearchQueryChange("")
                            },
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                    )
                }
            )
            val searchMovie = viewModel.movies.collectAsState().value
            if (searchMovie.isEmpty()) {
                Box(modifier = modifier.fillMaxSize()) {
                    if (movies.loadState.refresh is LoadState.Loading) {
                        Text("Loading", modifier = modifier.fillMaxWidth())
                    } else {
                        LazyColumn(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(
                                movies.itemCount,
                                key = movies.itemKey { it.id }
                            ) { index ->
                                val movie = movies[index]
                                if (movie != null) {
                                    MovieItem(
                                        movie = movie,
                                    )
                                }
                            }
                            item {
                                if (movies.loadState.append is LoadState.Loading) {
                                    Text("Loading", modifier = modifier.fillMaxWidth())
                                }
                                if (movies.loadState.append.endOfPaginationReached) {
                                    Text("End of page", modifier = modifier.fillMaxWidth())
                                }
                            }
                        }
                    }
                }
            } else {
                Box(modifier = modifier.fillMaxSize()) {
                    if (searchMovie.isEmpty()) {
                        Text("Result not found", modifier = modifier.fillMaxWidth())
                    } else {
                        LazyColumn(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(
                                searchMovie,
                            ) { movie ->
                                MovieItem(
                                    movie = movie,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}