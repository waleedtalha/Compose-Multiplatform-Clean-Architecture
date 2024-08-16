package com.app.cmpproject.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Compose wrapper scaffold for this project
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topAppBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {},
    snackBarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    isLoading: Boolean = false,
    error: String? = null
) {
    Scaffold(
        modifier = modifier,
        topBar = topAppBar,
        content = content,
        floatingActionButton = floatingActionButton,
        snackbarHost = snackBarHost
    )
}