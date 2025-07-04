package com.zfml.movievibe.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zfml.movievibe.data.local.dao.MovieDao
import com.zfml.movievibe.data.local.database.MovieDatabase
import com.zfml.movievibe.data.mappers.toDomain
import com.zfml.movievibe.data.remote.api.MovieApi
import com.zfml.movievibe.data.remoteMediator.PopularMoviesRemoteMediator
import com.zfml.movievibe.domain.model.Movie
import com.zfml.movievibe.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val db: MovieDatabase,
    private val api: MovieApi
) : MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PopularMoviesRemoteMediator(api,db),
            pagingSourceFactory = { db.movieDao().getMoviesByCategory("popular")}
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}