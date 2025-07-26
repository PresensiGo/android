package com.rizalanggoro.presensigo.presentation.attendance

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.composables.icons.lucide.Trash2
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.core.extensions.toLocalDateString
import com.rizalanggoro.presensigo.domain.Attendance
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AttendanceScreen(classroomID: Int) {
    val viewModel = koinViewModel<AttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    var selectedAttendance by remember { mutableStateOf<Attendance?>(null) }

    val navController = LocalNavController.current

    LaunchedEffect(state) {
        with(state) {
            when (action) {
                State.Action.GetAll -> {
                    if (status == StateStatus.Success)
                        viewModel.resetState()
                }

                State.Action.Delete -> {
                    if (status == StateStatus.Success) {
                        scope.launch { sheetState.hide() }
                            .invokeOnCompletion { isBottomSheetOpen = false }
                        viewModel.getAllAttendances()
                    }
                }

                else -> Unit
            }
        }
    }

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
                    headlineContent = { Text(it.date.toLocalDateString()) },
                    supportingContent = { Text(it.id.toString()) },
                    modifier = Modifier.combinedClickable(
                        onClick = {},
                        onLongClick = {
                            isBottomSheetOpen = true
                            selectedAttendance = it
                        }
                    )
                )
            }
        }
    }

    if (isBottomSheetOpen)
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetOpen = false },
            sheetState = sheetState
        ) {
            Column {
                Text("Options")
                ListItem(
                    leadingContent = { Icon(Lucide.Trash2, contentDescription = null) },
                    headlineContent = { Text("Delete") },
                    modifier = Modifier.clickable {
                        if (selectedAttendance != null)
                            viewModel.deleteAttendance(selectedAttendance!!)
                    }
                )
                Box {
                    Button(
                        onClick = {
                            scope.launch { sheetState.hide() }
                                .invokeOnCompletion { isBottomSheetOpen = false }
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
}