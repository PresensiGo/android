package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.DetailSubjectAttendanceViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Section2() {
    val viewModel = koinViewModel<DetailSubjectAttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(state.records) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(it.student.name)
                    Text(it.student.nis)
                    if (it.record.id > 0) Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(it.record.createdAt.formatDateTime())
                        Text(it.record.createdAt.formatDateTime("HH:mm"))
                    }
                    else Text("Belum presensi")
                }
            }
        }
    }
}