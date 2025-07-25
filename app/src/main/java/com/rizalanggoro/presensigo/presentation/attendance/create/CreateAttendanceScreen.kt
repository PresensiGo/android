package com.rizalanggoro.presensigo.presentation.attendance.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.RefreshCw
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.domain.AttendanceStatus
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAttendanceScreen() {
    val viewModel = koinViewModel<CreateAttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    var isUpdateStatusDialogOpen by remember { mutableStateOf(false) }

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Lucide.ArrowLeft,
                                contentDescription = null
                            )
                        }
                    },
                    title = { Text("Create Attendance") },
                    actions = {
                        IconButton(onClick = { viewModel.getAllStudents() }) {
                            Icon(Lucide.RefreshCw, contentDescription = null)
                        }
                    }
                )
                if (state.status == StateStatus.Loading)
                    LinearProgressIndicator()
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            ListItem(
                leadingContent = { Icon(Lucide.Calendar, contentDescription = null) },
                headlineContent = { Text("Tanggal") },
                supportingContent = { Text("Hari ni") },
                modifier = Modifier.clickable {}
            )
            HorizontalDivider()
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                AttendanceStatus.entries.toTypedArray().forEachIndexed { index, item ->
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
            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(state.students.filter { it.status == state.selectedFilter }) { index, item ->
                    ListItem(
                        leadingContent = {
                            Checkbox(
                                checked = item.isSelected,
                                onCheckedChange = {
                                    viewModel.changeSelected(
                                        student = item.student,
                                        isSelected = !item.isSelected
                                    )
                                }
                            )
                        },
                        headlineContent = { Text(item.student.name) },
                        supportingContent = { Text(item.student.nis) }
                    )
                }
            }
            OutlinedButton(onClick = { isUpdateStatusDialogOpen = true }) {
                Text("Change status")
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { viewModel.create() }
                ) {
                    Text("Simpan")
                }
            }
        }
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
}