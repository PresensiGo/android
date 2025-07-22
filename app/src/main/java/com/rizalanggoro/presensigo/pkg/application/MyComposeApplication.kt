package com.rizalanggoro.presensigo.pkg.application

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rizalanggoro.presensigo.internal.presentation.auth.AuthScreen
import com.rizalanggoro.presensigo.pkg.Routes
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