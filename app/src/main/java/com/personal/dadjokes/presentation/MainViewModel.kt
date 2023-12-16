package com.personal.dadjokes.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personal.dadjokes.domain.joke.JokesInfo
import com.personal.dadjokes.domain.repository.JokesRepository
import com.personal.dadjokes.domain.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val jokesRepository: JokesRepository
): ViewModel() {

    var jokesState by mutableStateOf(JokesState())
        private set

    private fun getJokes() {
        viewModelScope.launch {
            jokesState = jokesState.copy(
                isLoading = true,
                jokesError = null
            )

            var jokesInfo: JokesInfo? = null
            var jokesError: String? = null

            jokesRepository.getJokes().let { result ->
                when(result) {
                    is Resource.Error -> {
                        jokesError = result.message
                    }
                    is Resource.Success -> {
                        jokesInfo = result.data
                    }
                }
            }

            jokesState = jokesState.copy(
                jokesInfo = jokesInfo,
                isLoading = false,
                jokesError = jokesError
            )
        }
    }

    fun uiEvent(event: UiEvent) {
        when(event) {
            UiEvent.GetJokes -> { getJokes() }
        }
    }
}