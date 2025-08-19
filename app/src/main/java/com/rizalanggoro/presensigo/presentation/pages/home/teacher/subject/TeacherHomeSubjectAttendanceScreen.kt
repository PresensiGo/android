package com.rizalanggoro.presensigo.presentation.pages.home.teacher.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRightAlt
import androidx.compose.material.icons.rounded.Domain
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
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
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.openapi.models.Batch
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherHomeSubjectAttendanceScreen() {
    val viewModel = koinViewModel<TeacherHomeSubjectAttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            // top bar
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    "Presensi Mata Pelajaran",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    "Senin, 12 Agustus 2021",
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
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PullToRefreshBox(
                    isRefreshing = state.status.isLoading(),
                    onRefresh = { viewModel.getAllBatches() }
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
                                    "Daftar Angkatan",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    "Silahkan pilih angkatan yang akan dilakukan presensi mata pelajaran.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                                )
                            }
                        }

                        // loading state
                        if (state.status.isLoading())
                            items(3) {
                                BatchItem(isLoading = true)
                            }

                        // success state
                        if (state.status.isSuccess())
                            items(state.batches) {
                                BatchItem(batch = it) {
                                    navController.navigate(
                                        Routes.Attendance.Subject.Major(
                                            batchId = it.id
                                        )
                                    )
                                }
                            }
                    }
                }
            }
        }
    }
}

@Composable
private fun BatchItem(
    isLoading: Boolean = false,
    batch: Batch? = null,
    onClick: () -> Unit = {}
) {
    OutlinedCard(
        modifier = Modifier
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
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .then(
                            when (isLoading) {
                                true -> Modifier
                                    .clip(CircleShape)
                                    .shimmer()
                                    .background(MaterialTheme.colorScheme.outlineVariant)

                                else -> Modifier
                                    .clip(CircleShape)
                                    .background(
                                        MaterialTheme.colorScheme.onPrimaryContainer.copy(
                                            alpha = .48f
                                        )
                                    )
                            }
                        )
                ) {
                    Icon(
                        Icons.Rounded.Domain,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center),
                        tint = when (isLoading) {
                            true -> MaterialTheme.colorScheme.outlineVariant
                            else -> MaterialTheme.colorScheme.primaryContainer
                        },
                    )
                }
                Text(
                    batch?.name ?: "nama angkatan",
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
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "2 Jurusan",
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