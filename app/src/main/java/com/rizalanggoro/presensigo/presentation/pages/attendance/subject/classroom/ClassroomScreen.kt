package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.classroom

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
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.rizalanggoro.presensigo.core.constants.UiState
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.openapi.models.GetAllClassroomsByMajorIdItem
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassroomScreen() {
    val viewModel = koinViewModel<ClassroomViewModel>()
    val batchState by viewModel.batchState.collectAsState()
    val majorState by viewModel.majorState.collectAsState()
    val classroomsState by viewModel.classroomsState.collectAsState()

    val navController = LocalNavController.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
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
                    "Pilih Kelas",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // content
            PullToRefreshBox(
                isRefreshing = batchState.isLoading() || majorState.isLoading() || classroomsState.isLoading(),
                onRefresh = {
                    viewModel.getBatch()
                    viewModel.getMajor()
                    viewModel.getAllClassrooms()
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
                        Column(
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                                top = 24.dp,
                                bottom = 8.dp
                            )
                        ) {
                            Text(
                                "Daftar Kelas",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                "Berikut adalah kelas untuk ${
                                    with(majorState) {
                                        when (this) {
                                            is UiState.Success -> data.major.name
                                            else -> "-"
                                        }
                                    }
                                } ${
                                    with(batchState) {
                                        when (this) {
                                            is UiState.Success -> data.batch.name
                                            else -> "-"
                                        }
                                    }
                                }. " + "Silahkan pilih kelas yang akan dilakukan presensi mata pelajaran.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f),
                            )
                        }
                    }

                    with(classroomsState) {
                        when (this) {
                            is UiState.Loading -> items(3) {
                                ClassroomItem(isLoading = true)
                            }

                            is UiState.Success -> items(data.items) {
                                ClassroomItem(data = it) {
                                    navController.navigate(
                                        Routes.Attendance.Subject.Index(
                                            batchId = viewModel.params.batchId,
                                            majorId = viewModel.params.majorId,
                                            classroomId = it.classroom.id,
                                        )
                                    )
                                }
                            }

                            else -> Unit
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ClassroomItem(
    isLoading: Boolean = false,
    data: GetAllClassroomsByMajorIdItem? = null,
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
                                MaterialTheme.colorScheme.onPrimaryContainer.copy(
                                    alpha = .48f
                                )
                            )
                        }
                    )
            ) {
                Icon(
                    Icons.Rounded.Groups,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    tint = when (isLoading) {
                        true -> MaterialTheme.colorScheme.outlineVariant
                        else -> MaterialTheme.colorScheme.primaryContainer
                    }
                )
            }
            Row(verticalAlignment = Alignment.Bottom) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(
                        when (isLoading) {
                            true -> 4.dp
                            else -> 0.dp
                        }
                    )
                ) {
                    Text(
                        data?.classroom?.name ?: "loading nama kelas",
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
                        "${data?.studentCount ?: 0} Siswa",
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