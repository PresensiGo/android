package com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.MoreTime
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.DetailGeneralAttendanceViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Section3() {
    val viewModel = koinViewModel<DetailGeneralAttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    var isDeleteDialogOpen by remember { mutableStateOf(false) }

    LaunchedEffect(state.deleteStatus) {
        if (state.deleteStatus.isSuccess()) {
            navController.previousBackStackEntry?.savedStateHandle?.set("success", true)
            navController.popBackStack()
        }
    }

    Column(modifier = Modifier.padding(top = 16.dp)) {
        ListItem(
            leadingContent = {
                Icon(Icons.Rounded.MoreTime, contentDescription = null)
            },
            headlineContent = {
                Text("Ubah Tenggat Waktu")
            },
            supportingContent = {
                Text("12.12")
            },
            trailingContent = {
                Icon(Icons.Rounded.ChevronRight, contentDescription = null)
            },
            modifier = Modifier
                .clickable {}
                .padding(horizontal = 8.dp)
        )
        ListItem(
            leadingContent = {
                Icon(Icons.Rounded.Delete, contentDescription = null)
            },
            headlineContent = {
                Text("Hapus")
            },
            trailingContent = {
                Icon(Icons.Rounded.ChevronRight, contentDescription = null)
            },
            modifier = Modifier
                .clickable { isDeleteDialogOpen = true }
                .padding(horizontal = 8.dp)
        )
    }

    // dialogs
    if (isDeleteDialogOpen)
        AlertDialog(
            onDismissRequest = {
                if (!state.deleteStatus.isLoading())
                    isDeleteDialogOpen = false
            },
            title = { Text("Konfirmasi Hapus") },
            text = {
                Text(
                    "Apakah Anda yakin akan menghapus presensi kehadiran ini? " +
                            "Semua data yang terkait akan ikut terhapus. " +
                            "Tindakan yang Anda lakukan tidak dapat dipulihkan."
                )
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.deleteAttendance() },
                    enabled = !state.deleteStatus.isLoading()
                ) {
                    when (state.deleteStatus.isLoading()) {
                        true -> SmallCircularProgressIndicator()
                        else -> Text("Hapus")
                    }
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { isDeleteDialogOpen = false },
                    enabled = !state.deleteStatus.isLoading()
                ) {
                    Text("Batal")
                }
            }
        )
}