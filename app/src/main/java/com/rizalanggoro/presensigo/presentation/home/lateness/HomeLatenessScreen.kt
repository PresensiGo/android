package com.rizalanggoro.presensigo.presentation.home.lateness

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLatenessScreen() {
    val viewModel = koinViewModel<HomeLatenessViewModel>()
    val state by viewModel.state.collectAsState()

    val datePickerState = rememberDatePickerState()

    var isDatePickerDialogOpen by remember { mutableStateOf(false) }

    LaunchedEffect(state.status, state.action) {
        with(state) {
            when (action) {
                State.Action.Create -> {
                    if (status.isSuccess()) {
                        isDatePickerDialogOpen = false
                        viewModel.resetState()
                    }
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Keterlambatan") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { isDatePickerDialogOpen = true }) {
                Icon(Icons.Rounded.Add, contentDescription = null)
            }
        },
    ) {
        Column(modifier = Modifier.padding(it)) { }

        if (isDatePickerDialogOpen)
            DatePickerDialog(
                onDismissRequest = {
                    if (!state.status.isLoading())
                        isDatePickerDialogOpen = false
                },
                dismissButton = {
                    TextButton(
                        enabled = !state.status.isLoading(),
                        onClick = { isDatePickerDialogOpen = false }
                    ) { Text("Batal") }
                },
                confirmButton = {
                    TextButton(
                        enabled = !state.status.isLoading(),
                        onClick = {
                            val selectedTimeMillis = datePickerState.selectedDateMillis
                            if (selectedTimeMillis != null)
                                viewModel.create(selectedTimeMillis)
                        },
                        contentPadding = when (state.status.isLoading()) {
                            true -> ButtonDefaults.TextButtonWithIconContentPadding
                            else -> ButtonDefaults.TextButtonContentPadding
                        }
                    ) {
                        if (state.status.isLoading()) {
                            SmallCircularProgressIndicator()
                            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                        }
                        Text("Selesai")
                    }
                },
            ) {
                DatePicker(state = datePickerState)
            }
    }
}