package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.index

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowRightAlt
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.openapi.models.GetAllSubjectAttendancesItem
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectAttendanceScreen() {
    val viewModel = koinViewModel<SubjectAttendanceViewModel>()
    val batchState by viewModel.batchState.collectAsState()
    val majorState by viewModel.majorState.collectAsState()
    val classroomState by viewModel.classroomState.collectAsState()
    val attendancesState by viewModel.attendancesState.collectAsState()

    val navController = LocalNavController.current
    val backStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(backStackEntry?.savedStateHandle) {
        val savedStateHandle = backStackEntry?.savedStateHandle
        if (savedStateHandle != null && savedStateHandle.contains("success")) {
            viewModel.getAllAttendances()
            savedStateHandle.remove<Boolean>("success")
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Routes.Attendance.Subject.Create(
                            batchId = viewModel.params.batchId,
                            majorId = viewModel.params.majorId,
                            classroomId = viewModel.params.classroomId
                        )
                    )
                },
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            ) {
                Icon(Icons.Rounded.Add, contentDescription = null)
            }
        }
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
                    "Daftar Presensi",
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
//                Row(
//                    modifier = Modifier
//                        .clip(CircleShape)
//                        .background(MaterialTheme.colorScheme.surfaceVariant)
//                        .fillMaxWidth()
//                        .height(56.dp)
//                        .padding(horizontal = 4.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    listOf("Yang lalu", "Hari ini").mapIndexed { index, item ->
//                        val isSelected = index == 1
//                        Button(
//                            elevation = ButtonDefaults.buttonElevation(
//                                defaultElevation = 0.dp,
//                                pressedElevation = 0.dp,
//                                focusedElevation = 0.dp,
//                                hoveredElevation = 0.dp,
//                                disabledElevation = 0.dp
//                            ),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = when (isSelected) {
//                                    true -> MaterialTheme.colorScheme.background
//                                    else -> MaterialTheme.colorScheme.surfaceVariant
//                                },
//                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
//                            ),
//                            onClick = { },
//                            modifier = Modifier
//                                .weight(1f)
//                                .height(48.dp)
//                        ) {
//                            Text(item, fontWeight = FontWeight.SemiBold)
//                        }
//                    }
//                }
                PullToRefreshBox(
                    isRefreshing = batchState.isLoading() || majorState.isLoading() || classroomState.isLoading() || attendancesState.isLoading(),
                    onRefresh = {
                        viewModel.getBatch()
                        viewModel.getMajor()
                        viewModel.getClassroom()
                        viewModel.getAllAttendances()
                    },
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Column(
                                modifier = Modifier.padding(
                                    start = 24.dp,
                                    end = 24.dp,
                                    top = 24.dp,
                                    bottom = 8.dp
                                )
                            ) {
                                Text(
                                    "Daftar Presensi Mata Pelajaran",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    "Berikut adalah daftar presensi mata pelajaran, ${
                                        with(classroomState) {
                                            when (this) {
                                                is UiState.Success -> data.classroom.name
                                                else -> "-"
                                            }
                                        }
                                    }, ${
                                        with(majorState) {
                                            when (this) {
                                                is UiState.Success -> data.major.name
                                                else -> "-"
                                            }
                                        }
                                    }, ${
                                        with(batchState) {
                                            when (this) {
                                                is UiState.Success -> data.batch.name
                                                else -> "-"
                                            }
                                        }
                                    }.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f),
                                )
                            }
                        }

                        with(attendancesState) {
                            when (this) {
                                is UiState.Loading -> items(3) {
                                    SubjectAttendanceItem(isLoading = true)
                                }

                                is UiState.Success -> items(data.items) {
                                    SubjectAttendanceItem(data = it) {
                                        navController.navigate(
                                            Routes.Attendance.Subject.Detail(
                                                batchId = viewModel.params.batchId,
                                                majorId = viewModel.params.majorId,
                                                classroomId = viewModel.params.classroomId,
                                                attendanceId = it.subjectAttendance.id
                                            )
                                        )
                                    }
                                }

                                else -> Unit
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height((48 + 48).dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SubjectAttendanceItem(
    isLoading: Boolean = false,
    data: GetAllSubjectAttendancesItem? = null,
    onClick: () -> Unit = {}
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(CardDefaults.outlinedShape)
            .clickable(enabled = isLoading == false) { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .then(
                            when (isLoading) {
                                true -> Modifier
                                    .shimmer()
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier.background(
                                    MaterialTheme.colorScheme.onPrimaryContainer.copy(
                                        alpha = .48f
                                    )
                                )
                            }
                        )
                ) {
                    Icon(
                        Icons.Rounded.Category,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(16.dp),
                        tint = when (isLoading) {
                            true -> MaterialTheme.colorScheme.outlineVariant
                            else -> MaterialTheme.colorScheme.primaryContainer
                        }
                    )
                }

                Text(
                    data?.subject?.name ?: "nama mata pelajaran",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.then(
                        when (isLoading) {
                            true -> Modifier
                                .shimmer()
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.outlineVariant)

                            else -> Modifier
                        }
                    ),
                    color = when (isLoading) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> Color.Unspecified
                    }
                )
            }

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            when (isLoading) {
                                true -> 4.dp
                                else -> 0.dp
                            }
                        )
                    ) {
                        Text(
                            data?.subjectAttendance?.dateTime?.formatDateTime("EEEE, d MMMM yyyy")
                                ?: "loading tanggal attendance",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.then(
                                when (isLoading) {
                                    true -> Modifier
                                        .shimmer()
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.outlineVariant)

                                    else -> Modifier
                                }
                            ),
                            color = when (isLoading) {
                                true -> MaterialTheme.colorScheme.outlineVariant
                                else -> Color.Unspecified
                            }
                        )
                        Text(
                            data?.subjectAttendance?.dateTime?.formatDateTime("HH:mm")
                                ?: "loading waktu",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.then(
                                when (isLoading) {
                                    true -> Modifier
                                        .shimmer()
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.outlineVariant)

                                    else -> Modifier
                                }
                            ),
                            color = when (isLoading) {
                                true -> MaterialTheme.colorScheme.outlineVariant
                                else -> MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                            }
                        )
                    }

                    Text(
                        data?.creator?.name.let {
                            if (it.isNullOrBlank()) "Nama guru tidak ditemukan!"
                            else it
                        },
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.then(
                            when (isLoading) {
                                true -> Modifier
                                    .shimmer()
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier
                            }
                        ),
                        color = when (isLoading) {
                            true -> MaterialTheme.colorScheme.outlineVariant
                            else -> MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                        }
                    )
                }

                Icon(
                    Icons.AutoMirrored.Rounded.ArrowRightAlt,
                    contentDescription = null,
                    modifier = Modifier.then(
                        when (isLoading) {
                            true -> Modifier
                                .shimmer()
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.outlineVariant)

                            else -> Modifier
                        }
                    ),
                    tint = when (isLoading) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.outline
                    }
                )
            }
        }
    }
}