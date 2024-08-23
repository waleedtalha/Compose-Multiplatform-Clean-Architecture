package com.app.cmpproject.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.app.cmpproject.AppTheme
import com.app.cmpproject.di.init
import com.app.cmpproject.presentation.screens.login.LoginScreen
import org.koin.compose.KoinApplication

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    KoinApplication(application = {
        init()
    }) {
        AppTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
            Navigator(LoginScreen)
        }
    }
}