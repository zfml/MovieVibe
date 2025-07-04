package com.zfml.movievibe.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.zfml.movievibe.domain.model.Movie
import com.zfml.movievibe.domain.repository.MovieRepository
import com.zfml.movievibe.presentation.navigation.DetailMovieScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movie = savedStateHandle.toRoute<DetailMovieScreen>()

    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    init {
        getMovieDetail(movie.movieId)
    }

    private fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                _detailUiState.value = _detailUiState.value.copy(isLoading = true)

                val detail = movieRepository.getMovieDetail(movieId)

                _detailUiState.value = DetailUiState(
                    title = detail.title,
                    overview = detail.overview,
                    posterUrl = detail.posterUrl ?: "",
                    backdropUrl = detail.backdropUrl?: "",
                    releaseDate = detail.releaseDate,
                    rating = detail.rating,
                    isLoading = false
                )
            } catch (e: Exception) {
                _detailUiState.value = _detailUiState.value.copy(isLoading = false)
            }
        }
    }
}

data class DetailUiState(
    val title: String = "",
    val overview: String = "",
    val posterUrl: String = "",
    val backdropUrl: String = "",
    val releaseDate: String = "",
    val rating: Double = 0.0,
    val isLoading:Boolean = true
)