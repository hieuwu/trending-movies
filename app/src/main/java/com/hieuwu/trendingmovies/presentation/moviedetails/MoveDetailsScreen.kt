package com.hieuwu.trendingmovies.presentation.moviedetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.hieuwu.trendingmovies.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = stringResource(R.string.movie_details),
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                },
            )
        }
    ) { paddingValues ->
        val movieDetails = viewModel.movieDetails.collectAsState().value
        if (movieDetails != null) {
            Column(
                modifier = modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                val imageLoader = LocalContext.current.imageLoader.newBuilder()
                    .logger(DebugLogger())
                    .build()
                AsyncImage(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${movieId}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        )
                        .fillMaxWidth()
                        .height(256.dp)
                        .clip(shape = RoundedCornerShape(8)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movieDetails.backdropPath)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_spinner),
                    contentDescription = null,
                    imageLoader = imageLoader
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(text = movieDetails.title ?: "", style = MaterialTheme.typography.titleMedium,
                    modifier = modifier
                        .fillMaxWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/${movieDetails.title}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        )
                )
                Spacer(modifier = modifier.height(12.dp))
                val uriHandler = LocalUriHandler.current
                Text(
                    text = "For more details, please visit this page",
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            uriHandler.openUri(movieDetails.homepage ?: "N/A")
                        },
                    color = Color.Blue,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelMedium,
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/${movieDetails.releaseDate}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                    text = TextUtil.buildReleaseDate(movieDetails.releaseDate ?: "N/A"),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/${movieDetails.voteAverage}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                    text = TextUtil.buildVoteAverage(movieDetails.voteAverage ?: 0.0),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = movieDetails.overview ?: "N/A",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            Text(
                "Error loading move details",
                modifier = modifier.padding(paddingValues)
            )
        }
    }
}

object TextUtil {
    fun buildVoteAverage(vote: Double): String = "Vote average: $vote"
    fun buildReleaseDate(date: String): String = "Release date: $date"

}