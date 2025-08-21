package com.rizalanggoro.presensigo.presentation.pages.home.teacher.general

import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRightAlt
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.openapi.models.GetAllGeneralAttendancesItem
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherHomeGeneralAttendanceScreen() {
    val viewModel = koinViewModel<TeacherHomeGeneralAttendanceViewModel>()
    val attendances by viewModel.attendances.collectAsState()
    val deleteAttendance by viewModel.deleteAttendance.collectAsState()

    val navController = LocalNavController.current
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    var selectedForDelete by remember { mutableStateOf<GetAllGeneralAttendancesItem?>(null) }

    LaunchedEffect(currentBackStackEntry?.savedStateHandle) {
        val savedStateHandle = currentBackStackEntry?.savedStateHandle
        if (savedStateHandle != null && savedStateHandle.contains("success")) {
            viewModel.getAllGeneralAttendances()
            savedStateHandle.remove<Boolean>("success")
        }
    }

    LaunchedEffect(deleteAttendance) {
        if (deleteAttendance.isSuccess()) {
            selectedForDelete = null
            viewModel.getAllGeneralAttendances()
            viewModel.resetDeleteAttendance()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentWindowInsets = WindowInsets.statusBars,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.Attendance.General.Create)
                },
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            ) {
                Icon(Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
        ) {
            // top bar
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    "Presensi Kehadiran",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    Calendar.getInstance().timeInMillis.formatDateTime(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            // content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                    .background(MaterialTheme.colorScheme.background)
            ) {
                PullToRefreshBox(
                    isRefreshing = attendances.isLoading(),
                    onRefresh = { viewModel.getAllGeneralAttendances() }
                ) {
                    LazyColumn(
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
                                    "Daftar Presensi",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    "Berikut daftar presensi kehadiran siswa di sekolah. " +
                                            "Tekan dan tahan untuk menghapus presensi.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                                )
                            }
                        }

                        with(attendances) {
                            when (this) {
                                is UiState.Success -> items(data.items) {
                                    AttendanceItem(
                                        data = it,
                                        onClick = {
                                            navController.navigate(
                                                Routes.Attendance.General.Detail(
                                                    attendanceId = it.generalAttendance.id
                                                )
                                            )
                                        },
                                        onLongClick = {
                                            selectedForDelete = it
                                        }
                                    )
                                }

                                else -> items(3) { AttendanceItem(isLoading = true) }
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height((48 + 48).dp))
                        }
                    }
                }
            }
        }

        if (selectedForDelete != null) {
            AlertDialog(
                onDismissRequest = {
                    if (!deleteAttendance.isLoading()) {
                        selectedForDelete = null
                    }
                },
                title = { Text("Konfirmasi Hapus") },
                text = {
                    Text(
                        "Apakah Anda yakin akan menghapus presensi kehadiran pada " +
                                "${selectedForDelete!!.generalAttendance.datetime.formatDateTime()}? " +
                                "Tindakan yang Anda lakukan tidak dapat dipulihkan."
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteAttendance(
                                attendanceId = selectedForDelete!!.generalAttendance.id
                            )
                        },
                        enabled = !deleteAttendance.isLoading()
                    ) {
                        if (deleteAttendance.isLoading()) SmallCircularProgressIndicator()
                        else Text("Hapus")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            selectedForDelete = null
                        },
                        enabled = !deleteAttendance.isLoading()
                    ) {
                        Text("Batal")
                    }
                }
            )
        }
    }
}

@Composable
private fun AttendanceItem(
    isLoading: Boolean? = false,
    data: GetAllGeneralAttendancesItem? = null,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    OutlinedCard(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .clip(CardDefaults.outlinedShape)
            .combinedClickable(
                enabled = isLoading == false,
                onClick = { onClick() },
                onLongClick = { onLongClick() }
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
                    data?.generalAttendance?.datetime?.formatDateTime()
                        ?: "loading general attendance",
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
                    data?.generalAttendance?.datetime?.formatDateTime("HH:mm") ?: "loading",
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .48f))
                    ) {
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = null,
                            modifier = Modifier
                                .size(12.dp)
                                .align(Alignment.Center)
                                .then(
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
                                else -> MaterialTheme.colorScheme.primary
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