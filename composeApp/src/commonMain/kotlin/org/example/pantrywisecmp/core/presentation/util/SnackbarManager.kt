package org.example.pantrywisecmp.core.presentation.util

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import org.example.pantrywisecmp.core.domain.UiText

class SnackbarManager {
    private val _messages = Channel<UiText>(Channel.Factory.BUFFERED)
    val messages = _messages.receiveAsFlow()

    suspend fun show(message: UiText) {
        _messages.send(message)
    }
}