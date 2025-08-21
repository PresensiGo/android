package com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.sections.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.presentation.components.CustomChip
import com.rizalanggoro.presensigo.presentation.components.SecondaryButton
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.DetailGeneralAttendanceViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet() {
    val viewModel = koinViewModel<DetailGeneralAttendanceViewModel>()
    val batches by viewModel.batchesState.collectAsState()
    val majors by viewModel.majorsState.collectAsState()
    val classrooms by viewModel.classroomState.collectAsState()
    val selectedBatch by viewModel.selectedBatch.collectAsState()
    val selectedMajor by viewModel.selectedMajor.collectAsState()
    val selectedClassroom by viewModel.selectedClassroom.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = {
            viewModel.setFilterOpen(false)
        },
        containerColor = MaterialTheme.colorScheme.background,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Angkatan", style = MaterialTheme.typography.titleMedium)
                    FlowRow(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        with(batches) {
                            when (this) {
                                is UiState.Success ->
                                    data.items.map {
                                        val isSelected =
                                            it.batch.id == selectedBatch?.batch?.id
                                        CustomChip(
                                            isSelected = isSelected,
                                            text = it.batch.name,
                                            onClick = {
                                                viewModel.setSelectedBatch(
                                                    when (isSelected) {
                                                        true -> null
                                                        else -> it
                                                    }
                                                )
                                            }
                                        )
                                    }

                                else -> Array(3) {
                                    CustomChip(isLoading = true)
                                }
                            }
                        }
                    }
                }

                if (selectedBatch != null)
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Jurusan", style = MaterialTheme.typography.titleMedium)
                        FlowRow(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            with(majors) {
                                when (this) {
                                    is UiState.Success -> data.items.map {
                                        val isSelected =
                                            it.major.id == selectedMajor?.major?.id
                                        CustomChip(
                                            isSelected = isSelected,
                                            text = it.major.name,
                                            onClick = {
                                                viewModel.setSelectedMajor(
                                                    when (isSelected) {
                                                        true -> null
                                                        else -> it
                                                    }
                                                )
                                            }
                                        )
                                    }

                                    else -> Array(3) {
                                        CustomChip(isLoading = true)
                                    }
                                }
                            }
                        }
                    }

                if (selectedMajor != null)
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Kelas", style = MaterialTheme.typography.titleMedium)
                        FlowRow(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            with(classrooms) {
                                when (this) {
                                    is UiState.Success -> data.items.map {
                                        val isSelected =
                                            it.classroom.id == selectedClassroom?.classroom?.id
                                        CustomChip(
                                            isSelected = isSelected,
                                            text = it.classroom.name,
                                            onClick = {
                                                viewModel.setSelectedClassroom(
                                                    when (isSelected) {
                                                        true -> null
                                                        else -> it
                                                    }
                                                )
                                            }
                                        )
                                    }

                                    else -> Array(3) {
                                        CustomChip(isLoading = true)
                                    }
                                }
                            }
                        }
                    }
            }

            SecondaryButton(
                text = "Tutup"
            ) {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible)
                        viewModel.setFilterOpen(false)
                }
            }
        }
    }
}