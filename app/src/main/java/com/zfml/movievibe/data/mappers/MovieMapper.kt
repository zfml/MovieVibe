package com.zfml.movievibe.data.mappers

import com.zfml.movievibe.data.local.model.MovieEntity
import com.zfml.movievibe.data.remote.model.MovieDto
import com.zfml.movievibe.domain.model.Movie

fun MovieDto.toEntity(category: String) = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterPath = poster_path,
    backdropPath = backdrop_path ?: "",
    releaseDate = release_date,
    voteAverage = vote_average,
    category = category
)

fun MovieEntity.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterPath,
    backdropUrl = backdropPath,
    releaseDate = releaseDate,
    rating = voteAverage,
)

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = poster_path,
        backdropUrl = backdrop_path ?: "",
        releaseDate = release_date,
        rating = vote_average,
    )
}