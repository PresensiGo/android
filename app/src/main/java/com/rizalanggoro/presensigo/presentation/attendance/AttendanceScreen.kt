package com.rizalanggoro.presensigo.presentation.attendance

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.StateStatus
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(classroomID: Int) {
    val viewModel = koinViewModel<AttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Lucide.ArrowLeft, contentDescription = null)
                        }
                    },
                    title = { Text("Attendances") }
                )
                if (state.status == StateStatus.Loading)
                    LinearProgressIndicator()
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(
                    Routes.Attendance.Create(
                        classroomID = classroomID
                    )
                )
            }) {
                Icon(Lucide.Plus, contentDescription = null)
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(state.attendances) {
                ListItem(
                    headlineContent = { Text(it.date) },
                    modifier = Modifier.clickable {}
                )
            }
        }
    }
}