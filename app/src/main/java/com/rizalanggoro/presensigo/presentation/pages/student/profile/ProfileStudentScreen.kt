package com.rizalanggoro.presensigo.presentation.pages.student.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileStudentScreen() {
    val viewModel = koinViewModel<ProfileStudentViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    var isDialogLogoutOpen by remember { mutableStateOf(false) }

    LaunchedEffect(state.logoutStatus) {
        if (state.logoutStatus.isSuccess())
            navController.navigate(Routes.Auth) {
                popUpTo<Routes.Home.Student> { inclusive = true }
            }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Column(modifier = Modifier.padding(it)) {
            // app bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                }
                Text(
                    "Profil Saya",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                    .background(MaterialTheme.colorScheme.background),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .48f))
                            .size(48.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Column {
                            Text(
                                "Rizal Dwi Anggoro",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                "L0122142",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                            )
                        }
                    }
                }

                HorizontalDivider()

                Text(
                    "Informasi Siswa",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                )

                ListItem(
                    headlineContent = { Text("Sekolah") },
                    supportingContent = { Text("SMA Negeri 1 Magelang") },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                ListItem(
                    headlineContent = { Text("Kelas") },
                    supportingContent = { Text("X IPA 1") },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                ListItem(
                    headlineContent = { Text("Jurusan") },
                    supportingContent = { Text("Matematika") },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Text(
                    "Lainnya",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 8.dp)
                )

                ListItem(
                    leadingContent = {
                        Icon(
                            Icons.AutoMirrored.Rounded.Logout,
                            contentDescription = null
                        )
                    },
                    headlineContent = { Text("Keluar") },
                    trailingContent = {
                        Icon(
                            Icons.Rounded.ChevronRight,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .clickable { isDialogLogoutOpen = true }
                        .padding(horizontal = 8.dp)
                )
            }
        }

        // dialogs
        if (isDialogLogoutOpen)
            AlertDialog(
                onDismissRequest = {
                    if (!state.logoutStatus.isLoading())
                        isDialogLogoutOpen = false
                },
                title = { Text("Keluar") },
                text = { Text("Apakah Anda yakin akan keluar? NIS dan perangkat Anda akan tetap tertaut.") },
                confirmButton = {
                    Button(
                        onClick = { viewModel.logout() },
                        enabled = !state.logoutStatus.isLoading()
                    ) {
                        if (state.logoutStatus.isLoading()) SmallCircularProgressIndicator()
                        else Text("Keluar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { isDialogLogoutOpen = false },
                        enabled = !state.logoutStatus.isLoading()
                    ) {
                        Text("Batal")
                    }
                }
            )
    }
}