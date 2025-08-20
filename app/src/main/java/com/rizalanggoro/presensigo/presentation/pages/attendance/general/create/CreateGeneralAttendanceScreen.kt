package com.rizalanggoro.presensigo.presentation.pages.attendance.general.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Today
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
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
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.presentation.components.DatePickerModal
import com.rizalanggoro.presensigo.presentation.components.PrimaryButton
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
//    var note by remember { mutableStateOf("") }

    LaunchedEffect(state.status) {
        if (state.status.isSuccess()) {
            navController.previousBackStackEntry?.savedStateHandle?.set("success", true)
            navController.popBackStack()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer
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
                    "Tambah Presensi",
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "Masukkan beberapa informasi berikut untuk menambahkan presensi kehadiran baru.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f),
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)
                )
                Column {
                    ListItem(
                        leadingContent = {
                            Icon(Icons.Rounded.Today, contentDescription = null)
                        },
                        headlineContent = { Text("Tanggal") },
                        supportingContent = {
                            Text(
                                when (date != null) {
                                    true -> date!!.formatDateTime()
                                    else -> "Pilih tanggal"
                                }
                            )
                        },
                        trailingContent = {
                            Icon(
                                Icons.Rounded.ChevronRight,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .clickable { isDatePickerOpen = true }
                            .padding(horizontal = 8.dp),
                    )
                    ListItem(
                        leadingContent = {
                            Icon(
                                Icons.Rounded.AccessTime,
                                contentDescription = null
                            )
                        },
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
                        trailingContent = {
                            Icon(
                                Icons.Rounded.ChevronRight,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .clickable { isTimePickerOpen = true }
                            .padding(horizontal = 8.dp),
                    )
                }
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp),
//                    modifier = Modifier.padding(horizontal = 24.dp)
//                ) {
//                    Text(
//                        "Catatan Tambahan",
//                        style = MaterialTheme.typography.labelLarge,
//                        color = MaterialTheme.colorScheme.onBackground
//                    )
//                    OutlinedTextField(
//                        colors = OutlinedTextFieldDefaults.colors(
//                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
//                        ),
//                        shape = RoundedCornerShape(8.dp),
//                        value = note,
//                        onValueChange = { note = it },
//                        placeholder = { Text("Masukkan catatan tambahan") },
//                        minLines = 5,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                    )
//                }
//                if (state.status.isLoading())
//                    CircularProgressIndicator()
                Spacer(modifier = Modifier.weight(1f))
                PrimaryButton(text = "Simpan") {
                    if (date != null && time != null)
                        viewModel.create(
                            date = date!!,
                            time = time!!,
                        )
                }
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