package org.example.pantrywisecmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.pantrywisecmp.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "PantryWiseCMP",
        ) {
            App()
        }
    }
}