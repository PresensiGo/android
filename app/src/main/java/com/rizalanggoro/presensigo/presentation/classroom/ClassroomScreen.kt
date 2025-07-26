package com.rizalanggoro.presensigo.presentation.classroom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.StateStatus
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassroomScreen() {
    val viewModel = koinViewModel<ClassroomViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Lucide.ArrowLeft,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text("Classroom")
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            when (state.status) {
                StateStatus.Loading -> item {
                    CircularProgressIndicator()
                }

                StateStatus.Success -> items(state.classrooms) {
                    ListItem(
                        headlineContent = {
                            Text(it.classroom.name)
                        },
                        supportingContent = {
                            Text(it.major.name)
                        },
                        modifier = Modifier.clickable {
                            navController.navigate(
                                Routes.Attendance.List(
                                    classroomID = it.classroom.id
                                )
                            )
                        }
                    )
                }

                else -> Unit
            }
        }
    }
}