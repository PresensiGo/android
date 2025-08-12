package com.rizalanggoro.presensigo.presentation.pages.home.student

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomeScreen() {
    val viewModel = koinViewModel<StudentHomeViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current
    val context = LocalContext.current
    val currentBackStack by navController.currentBackStackEntryAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard Siswa") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.QrScanner)
                }
            ) {
                Icon(Icons.Rounded.QrCodeScanner, contentDescription = null)
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(currentBackStack?.savedStateHandle?.get<String>("qrcode") ?: "tidak ada")
        }
    }
}