package com.rizalanggoro.presensigo.presentation.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.LogOut
import com.composables.icons.lucide.Lucide
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.StateStatus
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen() {
    val viewModel = koinViewModel<SettingViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    var isLogoutDialogOpen by remember { mutableStateOf(false) }

    LaunchedEffect(state) {
        if (state.status == StateStatus.Success)
            navController.navigate(Routes.Auth) {
                popUpTo<Routes.Home>() { inclusive = true }
            }
    }

    Scaffold(
        topBar = {
            if (state.status != StateStatus.Loading)
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Lucide.ArrowLeft, contentDescription = null)
                        }
                    },
                    title = { Text("Pengaturan") }
                )
        }
    ) {
        when (state.status == StateStatus.Loading) {
            true -> LogoutLoadingContainer()
            else ->
                Column(modifier = Modifier.padding(it)) {
                    // logout
                    ListItem(
                        leadingContent = { Icon(Lucide.LogOut, contentDescription = null) },
                        headlineContent = { Text("Keluar") },
                        supportingContent = { Text("Hapus sesi autentikasi Anda saat ini") },
                        modifier = Modifier.clickable { isLogoutDialogOpen = true }
                    )
                }
        }
    }

    if (isLogoutDialogOpen)
        AlertDialog(
            onDismissRequest = { isLogoutDialogOpen = false },
            title = { Text("Keluar") },
            text = { Text("Apakah Anda yakin akan keluar dari sesi Anda saat ini?") },
            confirmButton = {
                TextButton(onClick = {
                    isLogoutDialogOpen = false
                    viewModel.logout()
                }) { Text("Keluar") }
            },
            dismissButton = {
                TextButton(onClick = {
                    isLogoutDialogOpen = false
                }) { Text("Batal") }
            }
        )
}

@Composable
private fun LogoutLoadingContainer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Mohon tunggu sebentar", style = MaterialTheme.typography.titleMedium)
        Text(
            "Kami sedang memproses permintaan Anda untuk keluar dari sesi saat ini",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
    }
}