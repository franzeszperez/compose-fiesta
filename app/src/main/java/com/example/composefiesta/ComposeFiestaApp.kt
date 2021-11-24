package com.example.composefiesta

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composefiesta.ui.theme.ComposeFiestaTheme

@Composable
fun ComposeFiestaApp(content: @Composable () -> Unit) {
    ComposeFiestaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}