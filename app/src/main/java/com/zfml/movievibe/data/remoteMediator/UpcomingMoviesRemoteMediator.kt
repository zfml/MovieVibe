package com.zfml.movievibe.data.remoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.zfml.movievibe.data.local.database.MovieDatabase
import com.zfml.movievibe.data.local.model.MovieEntity
import com.zfml.movievibe.data.mappers.toEntity
import com.zfml.movievibe.data.remote.api.MovieApi
@OptIn(ExperimentalPagingApi::class)
class UpcomingMoviesRemoteMediator (
    private val api: MovieApi,
    private val database: MovieDatabase
) : RemoteMediator<Int, MovieEntity>(){

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>,
    ): MediatorResult {
        val page = when(loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem  = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                (lastItem.id / state.config.pageSize) + 1
            }
        }
        try {
            val response = api.getUpcomingMovies(page)
            val movies = response.results.map { it.toEntity("up_coming") }

            database.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    database.movieDao().clearCategory("up_coming")
                }
                database.movieDao().insertAll(movies)
            }
            return MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        }catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

}