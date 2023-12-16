package com.personal.dadjokes.presentation

import com.personal.dadjokes.domain.joke.JokesInfo

data class JokesState(
    val jokesInfo: JokesInfo? = null,
    val isLoading: Boolean = false,
    val jokesError: String? = null
)
