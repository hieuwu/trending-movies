package com.hieuwu.trendingmovies.presentation.movielist.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.trendingmovies.domain.model.Movie

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie
) {
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
            Box(
                modifier = modifier
                    .width(80.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.error)
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