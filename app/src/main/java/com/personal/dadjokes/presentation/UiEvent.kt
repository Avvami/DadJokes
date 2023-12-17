package com.personal.dadjokes.presentation

sealed interface UiEvent {
    data object GetJokes: UiEvent
    data class SetNotifications(val receive: Boolean): UiEvent
    data class SetNotificationTime(val hour: Int, val minute: Int): UiEvent
}