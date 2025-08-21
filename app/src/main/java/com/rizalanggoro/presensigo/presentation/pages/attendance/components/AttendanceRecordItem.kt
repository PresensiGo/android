package com.rizalanggoro.presensigo.presentation.pages.attendance.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.openapi.models.ConstantsAttendanceStatus
import com.valentinilk.shimmer.shimmer

@Composable
fun AttendanceRecordItem(
    isLoading: Boolean = false,
    isAttended: Boolean = false,
    isLate: Boolean = false,
    studentName: String = "loading nama siswa",
    studentNIS: String = "loading nis",
    recordDateTime: String = "",
    recordStatus: ConstantsAttendanceStatus = ConstantsAttendanceStatus.AttendanceStatusAlpha,
    onClick: () -> Unit = {},
) {
    val isPresentLate = recordStatus == ConstantsAttendanceStatus.AttendanceStatusPresent && isLate

    Row(
        modifier = Modifier
            .clickable(enabled = isLoading == false) { onClick() }
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
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
                                true -> when (isPresentLate) {
                                    true -> MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = .48f)
                                    else -> MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .48f)
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
                        true -> Icons.Rounded.Person
                        else -> Icons.Rounded.Close
                    },
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    tint = when (isAttended) {
                        true -> when (isPresentLate) {
                            true -> MaterialTheme.colorScheme.tertiaryContainer
                            else -> MaterialTheme.colorScheme.primaryContainer
                        }

                        else -> MaterialTheme.colorScheme.onErrorContainer
                    }
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
                studentName,
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
                studentNIS,
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
                    else -> MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                }
            )
            if (isAttended) {
                val date = recordDateTime.formatDateTime("EEEE, d MMMM yyyy")
                val time = recordDateTime.formatDateTime("HH:mm")

                Text(
                    "$date - $time",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                )
            }
        }
    }
}