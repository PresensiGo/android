package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.index

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rizalanggoro.presensigo.core.compositional.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectAttendanceScreen() {
    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Presensi") }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(5) {
                SubjectAttendanceItem {
                    
                }
            }
        }
    }
}

@Composable
private fun SubjectAttendanceItem(onClick: () -> Unit) {
    OutlinedCard {

    }
}