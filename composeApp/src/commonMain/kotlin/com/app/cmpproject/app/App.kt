package com.app.cmpproject.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.app.cmpproject.AppTheme
import com.app.cmpproject.presentation.screens.login.LoginScreen

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    AppTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
        Navigator(LoginScreen)
    }
}