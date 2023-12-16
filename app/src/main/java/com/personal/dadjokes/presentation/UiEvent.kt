package com.personal.dadjokes.presentation

sealed interface UiEvent {
    data object GetJokes: UiEvent
}