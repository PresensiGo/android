package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.DetailSubjectAttendanceViewModel
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section1() {
    val viewModel = koinViewModel<DetailSubjectAttendanceViewModel>()
    val attendanceState by viewModel.attendanceState.collectAsState()
    val subjectState by viewModel.subjectState.collectAsState()
    val classroomState by viewModel.classroomState.collectAsState()
    val qrCodeState by viewModel.qrCode.collectAsState()

    with(attendanceState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                when (this@with) {
                    UiState.Loading -> 16.dp
                    else -> 0.dp
                }
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    when (this@with) {
                        UiState.Loading -> 4.dp
                        else -> 0.dp
                    }
                )
            ) {
                Text(
                    "${
                        with(subjectState) {
                            when (this) {
                                is UiState.Success -> data.subject.name
                                else -> "mata pelajaran"
                            }
                        }
                    } - ${
                        with(classroomState) {
                            when (this) {
                                is UiState.Success -> data.classroom.name
                                else -> "nama kelas"
                            }
                        }
                    }",
                    style = MaterialTheme.typography.titleMedium,
                    color = when (subjectState.isLoading() || classroomState.isLoading()) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground
                    },
                    modifier = when (subjectState.isLoading() || classroomState.isLoading()) {
                        true -> Modifier
                            .clip(CircleShape)
                            .shimmer()
                            .background(MaterialTheme.colorScheme.outlineVariant)

                        else -> Modifier
                    }
                )
                Text(
                    when (this@with) {
                        is UiState.Success -> data.subjectAttendance.dateTime.formatDateTime("EEEE, d MMMM yyyy - HH.mm")
                        else -> "loading tanggal dan waktu"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = when (this@with) {
                        is UiState.Loading -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                    },
                    modifier = when (this@with) {
                        UiState.Loading -> Modifier
                            .clip(CircleShape)
                            .shimmer()
                            .background(MaterialTheme.colorScheme.outlineVariant)

                        else -> Modifier
                    }
                )
                Text(
                    when (this@with) {
                        is UiState.Success -> data.creator.name.let {
                            if (it.isEmpty()) "Nama guru tidak ditemukan!"
                            else it
                        }

                        else -> "loading nama guru"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = when (this@with) {
                        is UiState.Loading -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                    },
                    modifier = when (this@with) {
                        UiState.Loading -> Modifier
                            .clip(CircleShape)
                            .shimmer()
                            .background(MaterialTheme.colorScheme.outlineVariant)

                        else -> Modifier
                    }
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    when (this@with) {
                        is UiState.Loading -> 16.dp
                        else -> 0.dp
                    }
                )
            ) {
                when (qrCodeState) {
                    null -> Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .shimmer()
                            .background(MaterialTheme.colorScheme.outlineVariant)
                    )

                    else -> Image(
                        qrCodeState!!.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxWidth()
                    )
                }

                Text(
                    when (this@with) {
                        is UiState.Success -> data.subjectAttendance.code
                        else -> "loading code subject attendance"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    modifier = when (this@with) {
                        is UiState.Loading -> Modifier
                            .clip(CircleShape)
                            .shimmer()
                            .background(MaterialTheme.colorScheme.outlineVariant)

                        else -> Modifier
                    },
                    color = when (this@with) {
                        UiState.Loading -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                    }
                )
            }
        }
    }
}