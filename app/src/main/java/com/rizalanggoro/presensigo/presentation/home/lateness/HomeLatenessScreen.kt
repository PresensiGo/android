package com.rizalanggoro.presensigo.presentation.home.lateness

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.core.extensions.toLocalDateString
import com.rizalanggoro.presensigo.domain.Lateness
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import com.rizalanggoro.presensigo.ui.theme.CardCornerShape
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLatenessScreen() {
    val viewModel = koinViewModel<HomeLatenessViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current
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
            Column {
                TopAppBar(
                    title = { Text("Keterlambatan") },
                    actions = {
                        IconButton(onClick = { viewModel.getAllLatenesses() }) {
                            Icon(Icons.Rounded.Refresh, contentDescription = null)
                        }
                    }
                )
                if (state.status.isLoading() && state.action == State.Action.GetAll)
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isDatePickerDialogOpen = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(state.latenesses) { index, item ->
                LatenessItem(
                    index = index,
                    itemsSize = state.latenesses.size,
                    lateness = item,
                    onClick = {
                        navController.navigate(
                            Routes.Lateness.Detail.Index(
                                latenessId = item.id
                            )
                        )
                    })
            }
        }

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

@Composable
private fun LatenessItem(index: Int, itemsSize: Int, lateness: Lateness, onClick: () -> Unit) {
    val shape = CardCornerShape.getShape(CardCornerShape.getPosition(index, itemsSize))

    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(shape)
            .clickable { onClick() },
        shape = shape
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(progress = { .3f })
            Column {
                Text(
                    lateness.date.toLocalDateString(),
                    style = MaterialTheme.typography.titleMedium
                )
                Text("3 siswa terlambat", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}