package com.hieuwu.trendingmovies.data.repository.impl

import com.hieuwu.trendingmovies.ImageUtil
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieDetailsEntity
import com.hieuwu.trendingmovies.data.network.movie.dto.MovieDetailsDto
import com.hieuwu.trendingmovies.domain.model.MovieDetails

fun MovieDetailsEntity.toDomainModel() = MovieDetails(
    backdropPath = ImageUtil.buildImageUrl(this.backdropPath ?: ""),
    homepage = this.homepage,
    id = this.id,
    overview = this.overview,
    releaseDate = this.releaseDate,
    title = this.title,
    voteAverage = this.voteAverage,
)

fun MovieDetailsDto.toEntity() = MovieDetailsEntity(
    backdropPath = ImageUtil.buildImageUrl(this.backdropPath ?: ""),
    homepage = this.homepage,
    id = this.id,
    overview = this.overview,
    releaseDate = this.releaseDate,
    title = this.title,
    voteAverage = this.voteAverage,
)