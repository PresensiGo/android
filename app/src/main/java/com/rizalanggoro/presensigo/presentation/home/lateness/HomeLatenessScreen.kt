package com.rizalanggoro.presensigo.presentation.home.lateness

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLatenessScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Keterlambatan") })
        }
    ) { }
}