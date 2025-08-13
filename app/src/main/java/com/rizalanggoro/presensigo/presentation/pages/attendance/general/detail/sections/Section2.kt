package com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import com.rizalanggoro.presensigo.openapi.models.GetAllGeneralAttendanceRecordsItem
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.DetailGeneralAttendanceViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Section2() {
    val viewModel = koinViewModel<DetailGeneralAttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(state.records) {
            RecordItem(data = it) {

            }
        }
    }
}

@Composable
private fun RecordItem(data: GetAllGeneralAttendanceRecordsItem, onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(data.student.name)
            Text(data.student.nis, style = MaterialTheme.typography.bodyMedium)
            if (data.record.id > 0) Text(
                data.record.createdAt.formatDateTime("EEEE, dd MMMM yyyy HH:mm"),
                style = MaterialTheme.typography.bodyMedium
            )
            else Text("Belum presensi", style = MaterialTheme.typography.bodyMedium)
        }
    }
}