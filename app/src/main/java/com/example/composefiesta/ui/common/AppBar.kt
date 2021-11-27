package com.example.composefiesta.ui.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.composefiesta.R

@Composable
fun AppBar(title: String = stringResource(id = R.string.app_name)) {
    TopAppBar(
        title = { Text(text = title) },
    )
}