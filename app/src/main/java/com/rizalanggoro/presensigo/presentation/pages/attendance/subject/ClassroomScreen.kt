package com.rizalanggoro.presensigo.presentation.pages.attendance.subject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassroomScreen() {
    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Kelas") }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(5) {
                ClassroomItem {
                    navController.navigate(
                        Routes.Attendance.Subject.Index(
                            batchId = 1,
                            majorId = 1,
                            classroomId = 1,
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ClassroomItem(onClick: () -> Unit) {
    OutlinedCard {
        Column {
            Text("Nama kelas")
        }
    }
}