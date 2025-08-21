package com.rizalanggoro.presensigo.presentation.pages.attendance.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.constants.AppAttendanceStatus
import com.rizalanggoro.presensigo.core.constants.attendanceStatuses
import com.rizalanggoro.presensigo.presentation.components.PrimaryButton
import com.rizalanggoro.presensigo.presentation.components.SecondaryButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAttendanceRecordBottomSheet(
    isLoading: Boolean = false,
    studentName: String = "",
    studentNIS: String = "",
    status: AppAttendanceStatus? = AppAttendanceStatus.Alpha,
    onClickStatus: (AppAttendanceStatus) -> Unit = {},
    onClickSave: () -> Unit = {},
    sheetState: SheetState,
    onDismissRequest: () -> Unit = {},
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedCard(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        studentName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        studentNIS,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                    )
                }
            }

            Column {
                attendanceStatuses.mapIndexed { index, it ->
                    ListItem(
                        leadingContent = {
                            RadioButton(
                                selected = status == it.appStatus,
                                onClick = null
                            )
                        },
                        headlineContent = { Text(it.title) },
                        modifier = Modifier
                            .clickable {
                                onClickStatus(it.appStatus)
                            }
                            .padding(horizontal = 8.dp),
                    )
                }
            }
            Column(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PrimaryButton(
                    text = "Simpan",
                    isLoading = isLoading
                ) {
                    onClickSave()
                }
                SecondaryButton(
                    text = "Batal",
                ) {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismissRequest()
                        }
                    }
                }
            }
        }
    }
}