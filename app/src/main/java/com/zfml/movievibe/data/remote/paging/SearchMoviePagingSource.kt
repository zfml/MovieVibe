package com.zfml.movievibe.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zfml.movievibe.data.mappers.toDomain
import com.zfml.movievibe.data.remote.api.MovieApi
import com.zfml.movievibe.domain.model.Movie

class SearchMoviePagingSource(
    private val api: MovieApi,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = api.searchMovies(query = query, page = page)
            val movies = response.results.map { it.toDomain() }
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null
}