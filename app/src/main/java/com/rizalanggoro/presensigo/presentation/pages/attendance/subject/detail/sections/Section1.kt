package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.DetailSubjectAttendanceViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Section1() {
    val viewModel = koinViewModel<DetailSubjectAttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        state.qrCodeBitmap.let {
            if (it != null)
                Image(
                    state.qrCodeBitmap!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .aspectRatio(1f)
                        .fillMaxWidth()
                )
        }
        Button(onClick = {}) {
            Text("Refresh QR Code")
        }
    }
}