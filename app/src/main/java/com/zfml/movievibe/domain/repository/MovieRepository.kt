package com.zfml.movievibe.domain.repository

import androidx.paging.PagingData
import com.zfml.movievibe.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository{

    fun getPopularMovies(): Flow<PagingData<Movie>>
}