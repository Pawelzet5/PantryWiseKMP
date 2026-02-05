package org.example.pantrywisecmp.core.presentation.model

sealed interface NavigationEvent {
    data object NavigateBack: NavigationEvent
}