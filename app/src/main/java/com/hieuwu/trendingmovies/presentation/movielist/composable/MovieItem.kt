package com.hieuwu.trendingmovies.presentation.movielist.composable

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.hieuwu.trendingmovies.R
import com.hieuwu.trendingmovies.domain.model.Movie
import com.hieuwu.trendingmovies.presentation.moviedetails.TextUtil

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .logger(DebugLogger())
        .build()
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        onClick = onItemClick
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${movie.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    )
                    .height(120.dp)
                    .clip(shape = RoundedCornerShape(8))
                    .aspectRatio(16 / 9f)
                    .weight(1f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.backdropPath)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_spinner),
                contentDescription = null,
                imageLoader = imageLoader
            )
            Column(
                modifier = modifier
                    .weight(1.0f)
                    .padding(8.dp)
            ) {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/${movie.title}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/${movie.releaseDate}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                    text = TextUtil.buildReleaseDate(movie.releaseDate),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/${movie.voteAverage}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        ),
                    text = TextUtil.buildVoteAverage(movie.voteAverage ?: 0.0),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}