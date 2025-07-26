package com.rizalanggoro.presensigo.presentation.attendance.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.extensions.toLocalDateString
import com.rizalanggoro.presensigo.domain.AttendanceStatus
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAttendanceScreen() {
    val viewModel = koinViewModel<DetailAttendanceViewModel>()
    val state by viewModel.state.collectAsState()
    val filteredItems by viewModel.filteredItems.collectAsState()

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
                    title = { Text("Detail Attendance") }
                )
                if (state.status == StateStatus.Loading)
                    LinearProgressIndicator()
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            ListItem(
                headlineContent = { Text("Tanggal") },
                supportingContent = {
                    Text(state.attendance.attendance.date.toLocalDateString())
                }
            )
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AttendanceStatus.entries.mapIndexed { index, item ->
                    val isSelected = state.selectedFilter == item
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

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (filteredItems.isEmpty())
                    item {
                        Text("Tidak ada data siswa")
                    }
                items(filteredItems) {
                    ListItem(
                        headlineContent = { Text(it.student.name) },
                        supportingContent = { Text("${it.student.nis} - ${it.attendanceDetail.status.title}") }
                    )
                }
            }
        }
    }
}