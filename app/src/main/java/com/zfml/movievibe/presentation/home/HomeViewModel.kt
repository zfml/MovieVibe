package com.zfml.movievibe.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.zfml.movievibe.domain.repository.MovieRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel(){
    val popularMovies = movieRepository.getPopularMovies().cachedIn(viewModelScope)
    val topRatedMovies = movieRepository.getTopRatedMovies().cachedIn(viewModelScope)
    val upComingMovies = movieRepository.getUpcomingMovies().cachedIn(viewModelScope)

}