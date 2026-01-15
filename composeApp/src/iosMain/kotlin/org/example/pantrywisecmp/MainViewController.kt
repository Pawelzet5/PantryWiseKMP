package org.example.pantrywisecmp

import androidx.compose.ui.window.ComposeUIViewController
import org.example.pantrywisecmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }