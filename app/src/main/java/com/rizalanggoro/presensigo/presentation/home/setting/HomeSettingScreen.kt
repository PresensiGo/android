package com.rizalanggoro.presensigo.presentation.home.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
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
import androidx.compose.ui.Modifier
import com.composables.icons.lucide.LogOut
import com.composables.icons.lucide.Lucide
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSettingScreen() {
    val viewModel = koinViewModel<HomeSettingViewModel>()
    val state by viewModel.state.collectAsState()

    var isLogoutDialogOpen by remember { mutableStateOf(false) }

    val navController = LocalNavController.current

    LaunchedEffect(state.status, state.action) {
        with(state) {
            when (action) {
                State.Action.Logout -> {
                    if (status.isSuccess()) {
                        isLogoutDialogOpen = false
                        navController.navigate(Routes.Auth) {
                            popUpTo<Routes.Home> { inclusive = true }
                        }
                    }
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pengaturan") })
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            ListItem(
                leadingContent = {
                    Icon(
                        Lucide.LogOut,
                        contentDescription = null,
                    )
                },
                headlineContent = { Text("Keluar") },
                modifier = Modifier.clickable {
                    isLogoutDialogOpen = true
                }
            )
        }
    }

    if (isLogoutDialogOpen)
        AlertDialog(
            onDismissRequest = {
                if (!state.status.isLoading())
                    isLogoutDialogOpen = false
            },
            title = { Text("Keluar") },
            text = { Text("Apakah Anda yakin akan keluar dari sesi Anda saat ini?") },
            confirmButton = {
                Button(
                    onClick = { viewModel.logout() },
                    enabled = !state.status.isLoading()
                ) {
                    if (state.status.isLoading()) SmallCircularProgressIndicator()
                    else Text("Keluar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { isLogoutDialogOpen = false },
                    enabled = !state.status.isLoading()
                ) {
                    Text("Batal")
                }
            }
        )
}