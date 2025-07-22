package com.rizalanggoro.presensigo.core.application

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.presentation.auth.AuthScreen
import com.rizalanggoro.presensigo.ui.theme.PresensiGoTheme

@Composable
fun MyComposeApplication() {
    PresensiGoTheme(darkTheme = false) {
        val navController = rememberNavController()

        NavHost(navController, startDestination = Routes.Auth) {
            composable<Routes.Auth> { AuthScreen() }
        }
    }
}