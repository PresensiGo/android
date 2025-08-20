package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.openapi.models.GetAllSubjectAttendanceRecordsItem
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.DetailSubjectAttendanceViewModel
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section2() {
    val viewModel = koinViewModel<DetailSubjectAttendanceViewModel>()
    val recordsState by viewModel.recordsState.collectAsState()

    PullToRefreshBox(
        isRefreshing = recordsState.isLoading(),
        onRefresh = { viewModel.getAllRecords() }
    ) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    "Sudah Presensi",
                    style = MaterialTheme.typography.titleSmall,
                    color = when (recordsState.isLoading()) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground
                    },
                    modifier = Modifier
                        .padding(start = 24.dp, bottom = 8.dp)
                        .then(
                            when (recordsState.isLoading()) {
                                true -> Modifier
                                    .clip(CircleShape)
                                    .shimmer()
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier
                            }
                        )
                )
            }
            with(recordsState) {
                when (this) {
                    is UiState.Success -> items(data.presentItems) {
                        Item(data = it)
                    }

                    else -> items(3) {
                        Item(isLoading = true)
                    }
                }
            }

            item {
                Text(
                    "Belum Presensi",
                    style = MaterialTheme.typography.titleSmall,
                    color = when (recordsState.isLoading()) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground
                    },
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp, bottom = 8.dp)
                        .then(
                            when (recordsState.isLoading()) {
                                true -> Modifier
                                    .clip(CircleShape)
                                    .shimmer()
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier
                            }
                        )
                )
            }
            with(recordsState) {
                when (this) {
                    is UiState.Success -> items(data.notYetItems) {
                        Item(data = it)
                    }

                    else -> items(3) {
                        Item(isLoading = true)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun Item(
    isLoading: Boolean = false,
    data: GetAllSubjectAttendanceRecordsItem? = null,
    onClick: () -> Unit = {}
) {
    val isAttended = (data?.record?.id ?: 0) > 0

    Box(
        modifier = Modifier
            .clickable(enabled = isLoading == false) { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
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
                                    true -> MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .48f)
                                    else -> MaterialTheme.colorScheme.errorContainer
                                }
                            )
                        }
                    )
            ) {
                if (!isLoading)
                    Icon(
                        when (isAttended) {
                            true -> Icons.Rounded.Check
                            else -> Icons.Rounded.Close
                        },
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center),
                        tint = when (isAttended) {
                            true -> MaterialTheme.colorScheme.primaryContainer
                            else -> MaterialTheme.colorScheme.onErrorContainer
                        },
                    )
            }
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(
                    when (isLoading) {
                        true -> 4.dp
                        else -> 0.dp
                    }
                )
            ) {
                Text(
                    data?.student?.name ?: "loading nama siswa",
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
                        else -> MaterialTheme.colorScheme.onBackground
                    }
                )
                Text(
                    data?.student?.nis ?: "loading nis siswa",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = when (isLoading) {
                        true -> Modifier
                            .shimmer()
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.outlineVariant)

                        else -> Modifier
                    },
                    color = when (isLoading) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                    }
                )
                if (isAttended) {
                    Text(
                        "${data?.record?.createdAt?.formatDateTime("EEEE, d MMMM yyyy") ?: "-"} - ${
                            data?.record?.createdAt?.formatDateTime(
                                "HH:mm"
                            ) ?: "-"
                        }",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                    )
                }
            }
        }
    }
}