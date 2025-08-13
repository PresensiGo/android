package com.rizalanggoro.presensigo.presentation.pages.attendance.general.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Today
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePickerState
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
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.presentation.components.DatePickerModal
import com.rizalanggoro.presensigo.presentation.components.TimePickerDialog
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGeneralAttendanceScreen() {
    val viewModel = koinViewModel<CreateGeneralAttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    var isDatePickerOpen by remember { mutableStateOf(false) }
    var isTimePickerOpen by remember { mutableStateOf(false) }

    var date by remember { mutableStateOf<Long?>(null) }
    var time by remember { mutableStateOf<TimePickerState?>(null) }
    var note by remember { mutableStateOf("") }

    LaunchedEffect(state.status) {
        if (state.status.isSuccess()) {
            navController.previousBackStackEntry?.savedStateHandle?.set("success", true)
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Presensi Kehadiran") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            ListItem(
                leadingContent = { Icon(Icons.Rounded.Today, contentDescription = null) },
                headlineContent = { Text("Tanggal") },
                supportingContent = {
                    Text(
                        when (date != null) {
                            true -> date!!.formatDateTime()
                            else -> "Pilih tanggal"
                        }
                    )
                },
                trailingContent = { Icon(Icons.Rounded.ChevronRight, contentDescription = null) },
                modifier = Modifier.clickable { isDatePickerOpen = true },
            )
            ListItem(
                leadingContent = { Icon(Icons.Rounded.AccessTime, contentDescription = null) },
                headlineContent = { Text("Waktu") },
                supportingContent = {
                    Text(
                        when (time != null) {
                            true -> String.format(
                                Locale("id", "ID"),
                                "%02d.%02d",
                                time!!.hour,
                                time!!.minute
                            )

                            else -> "Pilih waktu"
                        }
                    )
                },
                trailingContent = { Icon(Icons.Rounded.ChevronRight, contentDescription = null) },
                modifier = Modifier.clickable { isTimePickerOpen = true },
            )
            TextField(
                value = note,
                onValueChange = { note = it },
                placeholder = { Text("Masukkan catatan tambahan") },
                minLines = 5,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            if (state.status.isLoading())
                CircularProgressIndicator()
            Button(
                enabled = !state.status.isLoading(),
                onClick = {
                    if (date != null && time != null)
                        viewModel.create(
                            date = date!!,
                            time = time!!,
                            note = note
                        )
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp, top = 16.dp)
            ) {
                Text("Simpan")
            }
        }

        // modals
        if (isDatePickerOpen)
            DatePickerModal(
                initial = date,
                onDateSelected = {
                    if (it != null) date = it
                    isDatePickerOpen = false
                },
                onDismiss = {
                    isDatePickerOpen = false
                }
            )
        if (isTimePickerOpen)
            TimePickerDialog(
                initialState = time,
                onConfirm = {
                    time = it
                    isTimePickerOpen = false
                },
                onDismiss = {
                    isTimePickerOpen = false
                }
            )
    }
}