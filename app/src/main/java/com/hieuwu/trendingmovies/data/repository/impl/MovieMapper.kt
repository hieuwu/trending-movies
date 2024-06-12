package com.hieuwu.trendingmovies.data.repository.impl

import com.hieuwu.trendingmovies.ImageUtil
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity
import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDto
import com.hieuwu.trendingmovies.domain.model.Movie

fun MovieDto.toEntity() = MovieEntity(
    id = this.id,
    posterPath = ImageUtil.buildImageUrl(this.backdropPath ?: ""),
    title = this.title,
    voteAverage = this.voteAverage,
    releaseDate = this.releaseDate,
)

fun MovieEntity.toDomainModel() = Movie(
    id = this.id,
    backdropPath = this.posterPath,
    title = this.title,
    voteAverage = this.voteAverage,
    releaseDate = this.releaseDate ?: "",
)

fun MovieDto.toDomainModel() = Movie(
    id = this.id,
    backdropPath = ImageUtil.buildImageUrl(this.backdropPath ?: ""),
    title = this.title,
    voteAverage = this.voteAverage,
    releaseDate = this.releaseDate,
)