package com.rizalanggoro.presensigo.presentation.pages.attendance.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator

@Composable
fun DeleteAttendanceRecordDialog(
    isLoading: Boolean = false,
    studentName: String = "",
    onClickDelete: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = {
            if (!isLoading) {
                onDismissRequest()
            }
        },
        title = { Text("Konfirmasi Hapus") },
        text = { Text("Apakah Anda yakin akan menghapus presensi dari ${studentName}?") },
        confirmButton = {
            Button(
                onClick = {
                    onClickDelete()
                },
                enabled = !isLoading
            ) {
                if (isLoading) SmallCircularProgressIndicator()
                else Text("Hapus")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissRequest() },
                enabled = !isLoading
            ) {
                Text("Batal")
            }
        }
    )
}