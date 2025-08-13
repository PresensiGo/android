package com.rizalanggoro.presensigo.presentation.pages.attendance.subject

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MajorScreen() {
    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Daftar Jurusan") }, navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null
                    )
                }
            })
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(5) {
                MajorItem(onClick = {
                    navController.navigate(
                        Routes.Attendance.Subject.Classroom(
                            batchId = 1,
                            majorId = 1,
                        )
                    )
                })
            }
        }
    }
}

@Composable
private fun MajorItem(onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text("Nama Jurusan", style = MaterialTheme.typography.titleMedium)
        }
    }
}