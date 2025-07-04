package com.zfml.movievibe.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zfml.movievibe.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository

): ViewModel(){
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    val searchResults = _query
        .debounce(300) // avoid excessive calls
        .filter { it.isNotBlank() }
        .flatMapLatest { query ->
            repository.searchMovies(query)
        }
        .cachedIn(viewModelScope)

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }
}