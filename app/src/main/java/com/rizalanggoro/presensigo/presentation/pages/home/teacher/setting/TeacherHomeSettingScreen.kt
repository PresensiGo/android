package com.rizalanggoro.presensigo.presentation.pages.home.teacher.setting

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.BuildConfig
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import com.rizalanggoro.presensigo.ui.theme.CardCornerShape
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherHomeSettingScreen() {
    val viewModel = koinViewModel<HomeSettingViewModel>()
    val state by viewModel.state.collectAsState()

    var isLogoutDialogOpen by remember { mutableStateOf(false) }
    var isResetDialogOpen by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val navController = LocalNavController.current

    LaunchedEffect(state.status, state.action) {
        with(state) {
            when (action) {
                State.Action.Logout -> {
                    if (status.isSuccess()) {
                        isLogoutDialogOpen = false
//                        navController.navigate(Routes.Auth) {
//                            popUpTo<Routes.TeacherHome> { inclusive = true }
//                        }
                    }
                }

                State.Action.Reset -> {
                    if (status.isSuccess()) {
                        isResetDialogOpen = false
                        viewModel.resetState()
                        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
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
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // payload
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .size(48.dp)
                ) {
                    Icon(
                        Icons.Rounded.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                with(state.payload) {
                    Column {
                        Text(name, style = MaterialTheme.typography.titleMedium)
                        Text(email, style = MaterialTheme.typography.bodySmall)
                        Text(
                            "$schoolName - [$schoolCode]",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ElevatedCard(
                    shape = CardCornerShape.getShape(CardCornerShape.Position.Top)
                ) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        leadingContent = {
                            Icon(
                                Icons.Rounded.Upload,
                                contentDescription = null
                            )
                        },
                        headlineContent = { Text("Unggah Data") },
                        supportingContent = {
                            Text(
                                "Unggah data angkatan, jurusan, kelas, dan " +
                                        "siswa dalam bentuk excel file"
                            )
                        },
                        modifier = Modifier.clickable {
                        }
                    )
                }

                ElevatedCard(
                    shape = CardCornerShape.getShape(CardCornerShape.Position.Bottom)
                ) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        leadingContent = {
                            Icon(
                                Icons.Rounded.DeleteForever,
                                contentDescription = null,
                            )
                        },
                        headlineContent = { Text("Reset") },
                        supportingContent = { Text("Hapus semua data sekolah saat ini") },
                        modifier = Modifier.clickable {
                            isResetDialogOpen = true
                        }
                    )
                }
            }

            ElevatedCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                shape = CardCornerShape.getShape(CardCornerShape.Position.Single)
            ) {
                ListItem(
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                    leadingContent = {
                        Icon(
                            Icons.AutoMirrored.Rounded.Logout,
                            contentDescription = null,
                        )
                    },
                    headlineContent = { Text("Keluar") },
                    modifier = Modifier.clickable {
                        isLogoutDialogOpen = true
                    }
                )
            }

            Column {
                Text(
                    "Lainnya",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                ListItem(
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                    leadingContent = { Icon(Icons.Rounded.Tag, contentDescription = null) },
                    headlineContent = { Text("Release tag") },
                    supportingContent = { Text(BuildConfig.RELEASE_TAG) }
                )
            }
        }
    }

    if (isResetDialogOpen)
        AlertDialog(
            onDismissRequest = {
                if (!state.status.isLoading())
                    isResetDialogOpen = false
            },
            title = { Text("Reset") },
            text = {
                Text(
                    "Apakah Anda yakin akan mereset seluruh data? Beberapa data yang akan " +
                            "dihapus meliputi angkatan, jurusan, kelas, siswa, dan presensi siswa."
                )
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.reset() },
                    enabled = !state.status.isLoading()
                ) {
                    if (state.status.isLoading()) SmallCircularProgressIndicator()
                    else Text("Reset")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { isResetDialogOpen = false },
                    enabled = !state.status.isLoading()
                ) {
                    Text("Batal")
                }
            }
        )

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