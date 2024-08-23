package com.app.cmpproject

import androidx.compose.ui.window.ComposeUIViewController
import com.app.cmpproject.app.App
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(darkTheme = isDarkTheme, dynamicColor = false)
}