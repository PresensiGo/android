package com.rizalanggoro.presensigo.presentation.pages.home.teacher.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherHomeSettingScreen() {
    val viewModel = koinViewModel<TeacherHomeSettingViewModel>()
    val account by viewModel.account.collectAsState()
    val school by viewModel.school.collectAsState()
    val logout by viewModel.logout.collectAsState()

    var isLogoutDialogOpen by remember { mutableStateOf(false) }

    val navController = LocalNavController.current

    LaunchedEffect(logout) {
        if (logout.isSuccess()) {
            isLogoutDialogOpen = false
            navController.navigate(Routes.Auth) {
                popUpTo<Routes.Home.Teacher> { inclusive = true }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentWindowInsets = WindowInsets.statusBars,
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            // top bar
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    "Pengaturan",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // content
            PullToRefreshBox(
                isRefreshing = school.isLoading(),
                onRefresh = {
                    viewModel.getAccount()
                    viewModel.getSchool()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                    .background(MaterialTheme.colorScheme.background),
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // payload
                    item {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(
                                        MaterialTheme.colorScheme.onPrimaryContainer.copy(
                                            alpha = .48f
                                        )
                                    )
                                    .size(48.dp)
                            ) {
                                Icon(
                                    Icons.Rounded.Person,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            with(account) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        when (this) {
                                            is UiState.Success -> 0.dp
                                            else -> 4.dp
                                        }
                                    )
                                ) {
                                    Text(
                                        when (this@with) {
                                            is UiState.Success -> data.user.name
                                            else -> "loading nama pengguna"
                                        },
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = when (this@with) {
                                            is UiState.Success -> Modifier
                                            else -> Modifier
                                                .shimmer()
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colorScheme.outlineVariant)
                                        },
                                        color = when (this@with) {
                                            is UiState.Success -> MaterialTheme.colorScheme.onBackground
                                            else -> MaterialTheme.colorScheme.outlineVariant
                                        }
                                    )
                                    Text(
                                        when (this@with) {
                                            is UiState.Success -> data.user.email
                                            else -> "loading email pengguna"
                                        },
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = when (this@with) {
                                            is UiState.Success -> Modifier
                                            else -> Modifier
                                                .shimmer()
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colorScheme.outlineVariant)
                                        },
                                        color = when (this@with) {
                                            is UiState.Success -> MaterialTheme.colorScheme.onBackground.copy(
                                                alpha = .8f
                                            )

                                            else -> MaterialTheme.colorScheme.outlineVariant
                                        }
                                    )
                                }
                            }
                        }

                        HorizontalDivider()
                    }

                    item {
                        Text(
                            "Informasi Sekolah",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                        )

                        with(school) {
                            ListItem(
                                headlineContent = {
                                    Text("Nama Sekolah")
                                },
                                supportingContent = {
                                    Text(
                                        when (this) {
                                            is UiState.Success -> data.school.name
                                            else -> "loading nama sekolah"
                                        },
                                        modifier = when (this) {
                                            is UiState.Success -> Modifier
                                            else -> Modifier
                                                .padding(top = 4.dp)
                                                .shimmer()
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colorScheme.outlineVariant)
                                        },
                                        color = when (this) {
                                            is UiState.Success -> Color.Unspecified
                                            else -> MaterialTheme.colorScheme.outlineVariant
                                        }
                                    )
                                },
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                            )

                            ListItem(
                                headlineContent = { Text("Kode Sekolah") },
                                supportingContent = {
                                    Text(
                                        when (this) {
                                            is UiState.Success -> data.school.code
                                            else -> "loading nama sekolah"
                                        },
                                        modifier = when (this) {
                                            is UiState.Success -> Modifier
                                            else -> Modifier
                                                .padding(top = 4.dp)
                                                .shimmer()
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colorScheme.outlineVariant)
                                        },
                                        color = when (this) {
                                            is UiState.Success -> Color.Unspecified
                                            else -> MaterialTheme.colorScheme.outlineVariant
                                        }
                                    )
                                },
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }

                    item {
                        Text(
                            "Lainnya",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 8.dp)
                        )

                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            leadingContent = {
                                Icon(
                                    Icons.AutoMirrored.Rounded.Logout,
                                    contentDescription = null,
                                )
                            },
                            headlineContent = { Text("Keluar") },
                            modifier = Modifier
                                .clickable {
                                    isLogoutDialogOpen = true
                                }
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }

    if (isLogoutDialogOpen)
        AlertDialog(
            onDismissRequest = {
                if (!logout.isLoading()) {
                    isLogoutDialogOpen = false
                }
            },
            title = { Text("Keluar") },
            text = { Text("Apakah Anda yakin akan keluar dari sesi Anda saat ini?") },
            confirmButton = {
                Button(
                    onClick = { viewModel.logout() },
                    enabled = !logout.isLoading()
                ) {
                    if (logout.isLoading()) SmallCircularProgressIndicator()
                    else Text("Keluar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { isLogoutDialogOpen = false },
                    enabled = !logout.isLoading()
                ) {
                    Text("Batal")
                }
            }
        )
}