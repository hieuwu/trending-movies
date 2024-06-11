package com.hieuwu.trendingmovies.presentation.movielist.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.hieuwu.trendingmovies.R
import com.hieuwu.trendingmovies.domain.model.Movie

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .logger(DebugLogger())
        .build()
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            AsyncImage(
                modifier = modifier
                    .width(80.dp)
                    .height(120.dp)
                    .clip(shape = RoundedCornerShape(8)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterPath)
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
                    modifier = modifier.fillMaxWidth(),
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = modifier.fillMaxWidth(), text = movie.releaseDate,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        movie = Movie(
            id = 1,
            posterPath = null,
            title = "",
            voteAverage = null,
            releaseDate = ""
        )
    )
}