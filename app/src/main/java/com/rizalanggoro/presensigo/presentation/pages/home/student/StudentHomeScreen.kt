package com.rizalanggoro.presensigo.presentation.pages.home.student

import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.core.extensions.isAfterDateTime
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomeScreen() {
    val viewModel = koinViewModel<StudentHomeViewModel>()
    val subjectAttendances by viewModel.subjectAttendances.collectAsState()
    val generalAttendances by viewModel.generalAttendances.collectAsState()
    val processAttendance by viewModel.processAttendance.collectAsState()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val navController = LocalNavController.current
    val currentBackStack by navController.currentBackStackEntryAsState()

    var isProcessAttendanceOpen by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            it != SheetValue.Hidden
        }
    )

    LaunchedEffect(currentBackStack?.savedStateHandle) {
        val qrCode = currentBackStack?.savedStateHandle?.get<String>("qrcode")
        if (qrCode != null && qrCode.isNotEmpty()) {
            viewModel.processAttendance(qrCode)
            isProcessAttendanceOpen = true
            currentBackStack?.savedStateHandle?.remove<String>("qrcode")
        }
    }

    LaunchedEffect(processAttendance) {
        with(processAttendance) {
            when (this) {
                is UiState.Success -> {
                    viewModel.getGeneralAttendances()
                    viewModel.getSubjectAttendances()

                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            isProcessAttendanceOpen = false
                        }
                    }
                }

                is UiState.Failure -> {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            isProcessAttendanceOpen = false
                        }
                    }

                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.QrScanner)
                },
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            ) {
                Icon(Icons.Rounded.QrCodeScanner, contentDescription = null)
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            // app bar
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "PresensiGo",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        Calendar.getInstance().timeInMillis.formatDateTime("EEEE, d MMMM yyyy"),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                IconButton(
                    onClick = { navController.navigate(Routes.ProfileStudent) },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background.copy(alpha = .16f)),
                ) {
                    Icon(
                        Icons.Rounded.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            }

            // content
            PullToRefreshBox(
                isRefreshing = subjectAttendances.isLoading() || generalAttendances.isLoading(),
                onRefresh = {
                    viewModel.getSubjectAttendances()
                    viewModel.getGeneralAttendances()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                    .background(MaterialTheme.colorScheme.background),
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text(
                            "Presensi Kehadiran",
                            style = MaterialTheme.typography.titleMedium,
                            color = when (generalAttendances) {
                                is UiState.Success -> MaterialTheme.colorScheme.onBackground
                                else -> MaterialTheme.colorScheme.outlineVariant
                            },
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .padding(top = 24.dp)
                                .then(
                                    when (generalAttendances) {
                                        is UiState.Success -> Modifier
                                        else -> Modifier
                                            .clip(CircleShape)
                                            .shimmer()
                                            .background(MaterialTheme.colorScheme.outlineVariant)
                                    }
                                )
                        )
                    }

                    with(generalAttendances) {
                        when (this) {
                            is UiState.Success -> items(data.items) {
                                AttendanceItem(
                                    isAttended = it.generalAttendanceRecord.id > 0,
                                    attendanceDateTime = it.generalAttendance.datetime,
                                    recordDateTime = it.generalAttendanceRecord.dateTime,
                                )
                            }

                            else -> items(1) {
                                AttendanceItem(isLoading = true)
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        Text(
                            "Presensi Mata Pelajaran",
                            style = MaterialTheme.typography.titleMedium,
                            color = when (generalAttendances) {
                                is UiState.Success -> MaterialTheme.colorScheme.onBackground
                                else -> MaterialTheme.colorScheme.outlineVariant
                            },
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .then(
                                    when (generalAttendances) {
                                        is UiState.Success -> Modifier
                                        else -> Modifier
                                            .clip(CircleShape)
                                            .shimmer()
                                            .background(MaterialTheme.colorScheme.outlineVariant)
                                    }
                                )
                        )
                    }

                    with(subjectAttendances) {
                        when (this) {
                            is UiState.Success -> items(data.items) {
                                AttendanceItem(
                                    isAttended = it.subjectAttendanceRecord.id > 0,
                                    attendanceDateTime = it.subjectAttendance.dateTime,
                                    recordDateTime = it.subjectAttendanceRecord.dateTime,
                                    subjectName = it.subject.name,
                                    creatorName = "blom ada"
                                )
                            }

                            else -> items(3) {
                                AttendanceItem(isLoading = true)
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height((48 + 48).dp))
                    }
                }
            }
        }

        if (isProcessAttendanceOpen)
            ModalBottomSheet(
                onDismissRequest = {
                    if (!processAttendance.isLoading()) {
                        isProcessAttendanceOpen = false
                    }
                },
                sheetState = sheetState
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        Text(
                            "Loading...",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text(
                            "Mohon tunggu sebentar, kami sedang memproses presensi Anda",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f),
                            textAlign = TextAlign.Center
                        )
                    }
                    CircularProgressIndicator()
                }
            }
    }
}

@Composable
private fun AttendanceItem(
    isLoading: Boolean = false,
    isAttended: Boolean = false,
    attendanceDateTime: String = "",
    recordDateTime: String = "",
    subjectName: String = "",
    creatorName: String = "",
) {
    var isLate = false
    if (attendanceDateTime.isNotEmpty() && recordDateTime.isNotEmpty()) {
        isLate = recordDateTime.isAfterDateTime(attendanceDateTime)
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .then(
                            when (isLoading) {
                                true -> Modifier
                                    .shimmer()
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier.background(
                                    when (isAttended) {
                                        true -> when (isLate) {
                                            true -> MaterialTheme.colorScheme.onTertiaryContainer.copy(
                                                alpha = .48f
                                            )

                                            else -> MaterialTheme.colorScheme.onPrimaryContainer.copy(
                                                alpha = .48f
                                            )
                                        }

                                        else -> MaterialTheme.colorScheme.errorContainer
                                    }
                                )
                            }
                        )
                ) {
                    if (!isLoading)
                        Icon(
                            when (isAttended) {
                                true -> when (isLate) {
                                    true -> Icons.Rounded.Warning
                                    else -> Icons.Rounded.Check
                                }

                                else -> Icons.Rounded.Close
                            },
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.Center),
                            tint = when (isAttended) {
                                true -> when (isLate) {
                                    true -> MaterialTheme.colorScheme.tertiaryContainer
                                    else -> MaterialTheme.colorScheme.primaryContainer
                                }

                                else -> MaterialTheme.colorScheme.onErrorContainer
                            }
                        )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        when (isLoading) {
                            true -> 4.dp
                            else -> 0.dp
                        }
                    ),
                ) {
                    Text(
                        when (isLoading) {
                            true -> "loading attendance datetime"
                            else -> attendanceDateTime.formatDateTime()
                        },
                        style = MaterialTheme.typography.titleMedium,
                        modifier = when (isLoading) {
                            true -> Modifier
                                .shimmer()
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.outlineVariant)

                            else -> Modifier
                        },
                        color = when (isLoading) {
                            true -> MaterialTheme.colorScheme.outlineVariant
                            else -> Color.Unspecified
                        }
                    )
                    if (subjectName.isNotEmpty())
                        Text(
                            "$subjectName - $creatorName",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = when (isLoading) {
                                true -> Modifier
                                    .shimmer()
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier
                            },
                            color = when (isLoading) {
                                true -> MaterialTheme.colorScheme.outlineVariant
                                else -> Color.Unspecified
                            }
                        )
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = .72f))

            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = .24f))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        "Tenggat",
                        style = MaterialTheme.typography.labelMedium,
                        color = when (isLoading) {
                            true -> MaterialTheme.colorScheme.outlineVariant
                            else -> MaterialTheme.colorScheme.primary
                        },
                        modifier = when (isLoading) {
                            true -> Modifier
                                .shimmer()
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.outlineVariant)

                            else -> Modifier
                        },
                    )
                    Text(
                        when (isLoading) {
                            true -> "loading"
                            else -> attendanceDateTime.formatDateTime("HH:mm")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = when (isLoading) {
                            true -> MaterialTheme.colorScheme.outlineVariant
                            else -> Color.Unspecified
                        },
                        modifier = when (isLoading) {
                            true -> Modifier
                                .shimmer()
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.outlineVariant)

                            else -> Modifier
                        },
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        "Masuk",
                        style = MaterialTheme.typography.labelMedium,
                        color = when (isLoading) {
                            true -> MaterialTheme.colorScheme.outlineVariant
                            else -> MaterialTheme.colorScheme.primary
                        },
                        modifier = when (isLoading) {
                            true -> Modifier
                                .shimmer()
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.outlineVariant)

                            else -> Modifier
                        },
                    )
                    Text(
                        when (isLoading) {
                            true -> "loading"
                            else -> when (isAttended) {
                                true -> recordDateTime.formatDateTime("HH:mm")
                                else -> "-"
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = when (isLoading) {
                            true -> MaterialTheme.colorScheme.outlineVariant
                            else -> Color.Unspecified
                        },
                        modifier = when (isLoading) {
                            true -> Modifier
                                .shimmer()
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.outlineVariant)

                            else -> Modifier
                        },
                    )
                }
            }
        }
    }
}