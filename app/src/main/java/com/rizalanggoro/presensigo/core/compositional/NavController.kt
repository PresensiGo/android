package com.rizalanggoro.presensigo.core.compositional

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> {
    error("No nav controller provided")
}