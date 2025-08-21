package com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.sections

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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.openapi.models.GetAllGeneralAttendanceRecordsItem
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.DetailGeneralAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.sections.components.FilterBottomSheet
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section2() {
    val viewModel = koinViewModel<DetailGeneralAttendanceViewModel>()
    val state by viewModel.state.collectAsState()
    val isFilterOpen by viewModel.isFilterOpen.collectAsState()

    PullToRefreshBox(
        isRefreshing = false,
        onRefresh = {},
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // loading state
            if (state.getAttendanceRecordsStatus.isLoading())
                items(3) {
                    Item(isLoading = true)
                }

            // success state
            if (state.getAttendanceRecordsStatus.isSuccess())
                items(state.records) {
                    Item(data = it) {

                    }
                }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // bottom sheet
        if (isFilterOpen)
            FilterBottomSheet()
    }
}

@Composable
private fun Item(
    isLoading: Boolean = false,
    data: GetAllGeneralAttendanceRecordsItem? = null,
    onClick: () -> Unit = {}
) {
    val isAttended = (data?.record?.id ?: 0) > 0

    OutlinedCard(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .clip(CardDefaults.outlinedShape)
            .clickable(enabled = isLoading == false) { onClick() }
    ) {
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
                        else -> Color.Unspecified
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