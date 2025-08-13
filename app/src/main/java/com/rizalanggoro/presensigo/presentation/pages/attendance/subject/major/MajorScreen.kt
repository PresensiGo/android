package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.major

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.openapi.models.Major
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MajorScreen() {
    val viewModel = koinViewModel<MajorViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Daftar Jurusan") }, navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
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
            items(state.majors) {
                MajorItem(major = it) {
                    navController.navigate(
                        Routes.Attendance.Subject.Classroom(
                            batchId = viewModel.params.batchId,
                            majorId = it.id,
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun MajorItem(major: Major, onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(major.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}