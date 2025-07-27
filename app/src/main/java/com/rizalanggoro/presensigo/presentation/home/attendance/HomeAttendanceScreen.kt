package com.rizalanggoro.presensigo.presentation.home.attendance

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAttendanceScreen() {
    val viewModel = koinViewModel<HomeAttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Presensi") }
                )
                if (state.status.isLoading())
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(state.batches) {
                ListItem(
                    headlineContent = { Text(it.name) },
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.Classroom(batchId = it.id))
                    }
                )
            }
        }
    }
}