package com.rizalanggoro.presensigo.presentation.attendance.create

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.window.Dialog
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Box
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.Check
import com.composables.icons.lucide.CheckCheck
import com.composables.icons.lucide.ChevronRight
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.RefreshCw
import com.composables.icons.lucide.User
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.extensions.toLocalDateString
import com.rizalanggoro.presensigo.domain.AttendanceStatus
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAttendanceScreen() {
    val viewModel = koinViewModel<CreateAttendanceViewModel>()
    val state by viewModel.state.collectAsState()
    val filteredItems by viewModel.filteredItems.collectAsState()
    val selectedItemsCount by viewModel.selectedItemsCount.collectAsState()

    var isUpdateStatusDialogOpen by remember { mutableStateOf(false) }
    var isDatePickerDialogOpen by remember { mutableStateOf(false) }
    var isConfirmDialogOpen by remember { mutableStateOf(false) }

    val navController = LocalNavController.current
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(state.action, state.status) {
        with(state) {
            when (action) {
                State.Action.Create -> {
                    if (status == StateStatus.Success) {
                        isConfirmDialogOpen = false
                        navController.popBackStack()
                    }
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Lucide.ArrowLeft,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    title = { Text("Kehadiran Baru") },
                    actions = {
                        IconButton(onClick = { viewModel.getAllStudents() }) {
                            Icon(
                                Lucide.RefreshCw,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                )
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AttendanceStatus.entries.toTypedArray().mapIndexed { index, item ->
                        val isSelected = item == state.selectedFilter
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = AttendanceStatus.entries.size
                            ),
                            selected = isSelected,
                            onClick = { viewModel.changeFilter(item) },
                            label = {
                                Text(
                                    when (isSelected) {
                                        true -> item.title
                                        else -> item.shortTitle
                                    }
                                )
                            }
                        )
                    }
                }

                AnimatedVisibility(visible = selectedItemsCount > 0) {
                    val isSelectedAll = selectedItemsCount == filteredItems.size
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = { viewModel.changeSelectedAll(!isSelectedAll) }) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Icon(
                                    Lucide.CheckCheck,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    when (isSelectedAll) {
                                        true -> "Hapus pilihan"
                                        else -> "Pilih semua"
                                    }
                                )
                            }
                        }
                        TextButton(onClick = { isUpdateStatusDialogOpen = true }) {
                            Text("Ubah status")
                        }
                    }
                }
            }
            AnimatedVisibility(visible = selectedItemsCount > 0) {
                HorizontalDivider()
            }

            if (state.action == State.Action.GetAll && state.status.isLoading())
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            else {
                if (filteredItems.isEmpty())
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterVertically
                        )
                    ) {
                        Icon(
                            Lucide.Box,
                            contentDescription = null,
                            modifier = Modifier.size(96.dp),
                            tint = MaterialTheme.colorScheme.outlineVariant
                        )
                        Text(
                            "Belum ada siswa dalam daftar ${state.selectedFilter.title.lowercase()}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                else
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        itemsIndexed(filteredItems) { index, item ->
                            val isSelected = item.isSelected

                            ListItem(
                                modifier = Modifier.clickable {
                                    viewModel.changeSelected(
                                        student = item.student,
                                        isSelected = !isSelected
                                    )
                                },
                                leadingContent = {
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(40.dp)
                                            .background(
                                                when (isSelected) {
                                                    true -> MaterialTheme.colorScheme.primary
                                                    else -> MaterialTheme.colorScheme.primaryContainer
                                                }
                                            )

                                    ) {
                                        Icon(
                                            when (isSelected) {
                                                true -> Lucide.Check
                                                else -> Lucide.User
                                            },
                                            contentDescription = null,
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .size(20.dp),
                                            tint = when (isSelected) {
                                                true -> MaterialTheme.colorScheme.onPrimary
                                                else -> MaterialTheme.colorScheme.onPrimaryContainer
                                            }
                                        )
                                    }
                                },
                                headlineContent = { Text(item.student.name) },
                                supportingContent = { Text(item.student.nis) }
                            )
                        }
                    }
            }

            HorizontalDivider()

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // date
                ListItem(
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .size(40.dp)
                        ) {
                            Icon(
                                Lucide.Calendar,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Center),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    },
                    headlineContent = { Text("Tanggal") },
                    supportingContent = {
                        Text(datePickerState.selectedDateMillis.let {
                            when (it == null) {
                                true -> "Pilih tanggal kehadiran"
                                else -> it.toLocalDateString()
                            }
                        })
                    },
                    trailingContent = {
                        Icon(
                            Lucide.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable { isDatePickerDialogOpen = true }
                )

                // button save
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    onClick = { isConfirmDialogOpen = true }
                ) {
                    Text("Simpan")
                }
            }
        }
    }

    if (isDatePickerDialogOpen)
        DatePickerDialog(
            onDismissRequest = { isDatePickerDialogOpen = false },
            dismissButton = {
                TextButton(onClick = { isDatePickerDialogOpen = false }) {
                    Text("Batal")
                }
            },
            confirmButton = {
                TextButton(onClick = { isDatePickerDialogOpen = false }) {
                    Text("Selesai")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }

    if (isUpdateStatusDialogOpen)
        Dialog(
            onDismissRequest = { isUpdateStatusDialogOpen = false }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Ubah Status Kehadiran", style = MaterialTheme.typography.titleLarge)
                    LazyColumn {
                        items(AttendanceStatus.entries.toTypedArray()) {
                            ListItem(
                                headlineContent = { Text(it.title) },
                                modifier = Modifier.clickable {
                                    viewModel.updateStatusBatch(
                                        status = it
                                    )
                                    isUpdateStatusDialogOpen = false
                                }
                            )
                        }
                    }
                    TextButton(onClick = { isUpdateStatusDialogOpen = false }) {
                        Text("Batal")
                    }
                }
            }
        }

    if (isConfirmDialogOpen)
        AlertDialog(
            onDismissRequest = {
                if (!state.status.isLoading())
                    isConfirmDialogOpen = false
            },
            title = { Text("Konfirmasi") },
            text = { Text("Apakah Anda yakin akan menyimpan kehadiran ini?") },
            dismissButton = {
                TextButton(
                    onClick = {
                        isConfirmDialogOpen = false
                    },
                    enabled = !state.status.isLoading()
                ) { Text("Batal") }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val selectedDate = datePickerState.selectedDateMillis
                        if (selectedDate != null)
                            viewModel.create(date = selectedDate)
                    },
                    enabled = !state.status.isLoading()
                ) {
                    when (state.status.isLoading()) {
                        true -> SmallCircularProgressIndicator()
                        else -> Text("Simpan")
                    }
                }
            }
        )
}