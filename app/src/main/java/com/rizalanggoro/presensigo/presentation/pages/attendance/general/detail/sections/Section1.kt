package com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.DetailGeneralAttendanceViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Section1() {
    val viewModel = koinViewModel<DetailGeneralAttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Senin, 12 Juni 2023 - 10.10",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            state.qrCodeBitmap.let {
                if (it != null)
                    Image(
                        it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
            }
            Text(
                state.attendance?.code ?: "-",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
            )
        }
    }
}