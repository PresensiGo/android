package com.rizalanggoro.presensigo.presentation.pages.attendance.subject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSubjectAttendanceScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Presensi") }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}