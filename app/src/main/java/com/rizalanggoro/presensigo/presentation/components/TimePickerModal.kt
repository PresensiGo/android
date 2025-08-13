package com.rizalanggoro.presensigo.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    initialState: TimePickerState? = null,
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = initialState?.hour ?: currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = initialState?.minute ?: currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Batal")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(
                        timePickerState
                    )
                }
            ) {
                Text("Selesai")
            }
        },
        text = {
            TimePicker(
                state = timePickerState,
            )
        }
    )
}