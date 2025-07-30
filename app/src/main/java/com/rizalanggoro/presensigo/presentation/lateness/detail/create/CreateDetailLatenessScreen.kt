package com.rizalanggoro.presensigo.presentation.lateness.detail.create

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.presentation.components.SelectedAvatars
import com.rizalanggoro.presensigo.ui.theme.CardCornerShape
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateDetailLatenessScreen() {
    val viewModel = koinViewModel<CreateDetailLatenessViewModel>()
    val state by viewModel.state.collectAsState()
    val items by viewModel.studentsWithSelectionState.collectAsState()

    val navController = LocalNavController.current

    var keyword by remember { mutableStateOf("") }

    LaunchedEffect(state.action, state.status) {
        with(state) {
            when (action) {
                State.Action.Create -> {
                    if (status.isSuccess()) {
                        viewModel.resetState()
                        navController.popBackStack()
                    }
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = { Text("Tambah Siswa") })
        }
    ) {
        Column(modifier = Modifier.padding(it), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    enabled = !state.status.isLoading(),
                    value = keyword,
                    onValueChange = { keyword = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Masukkan nama atau NIS") },
                    maxLines = 1,
                    singleLine = true
                )
                FilledTonalIconButton(
                    enabled = !state.status.isLoading(),
                    onClick = { viewModel.searchStudents(keyword = keyword) }
                ) {
                    with(state) {
                        when (state.action == State.Action.FindStudents && state.status.isLoading()) {
                            true -> CircularProgressIndicator(
                                modifier = Modifier.size(ButtonDefaults.IconSize),
                                strokeWidth = 3.dp
                            )

                            else -> Icon(Icons.Rounded.Search, contentDescription = null)
                        }
                    }
                }
            }

            // students
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(items) { index, item ->
                    StudentItem(
                        index = index,
                        itemsSize = state.students.size,
                        item = item,
                        onClick = {
                            viewModel.selectStudent(
                                studentId = item.data.student.id,
                                isSelected = !item.isSelected
                            )
                        }
                    )
                }
            }

            // button save
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AnimatedVisibility(visible = state.selectedStudentIds.isNotEmpty()) {
                    SelectedAvatars(
                        itemsCount = state.selectedStudentIds.size,
                        maxAvatarsCount = 3
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { if (state.selectedStudentIds.isNotEmpty()) viewModel.create() },
                    enabled = !(state.action == State.Action.Create && state.status.isLoading()),
                    contentPadding = when (state.action == State.Action.Create && state.status.isLoading()) {
                        true -> ButtonDefaults.ButtonWithIconContentPadding
                        else -> ButtonDefaults.ContentPadding
                    }
                ) {
                    if (state.action == State.Action.Create && state.status.isLoading()) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            strokeWidth = 3.dp
                        )
                        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                    }
                    Text("Simpan")
                }
            }
        }
    }
}

@Composable
private fun StudentItem(index: Int, itemsSize: Int, item: State.Item, onClick: () -> Unit) {
    val shape = CardCornerShape.getShape(CardCornerShape.getPosition(index, itemsSize))

    ElevatedCard(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(shape)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        when (item.isSelected) {
                            true -> MaterialTheme.colorScheme.primaryContainer
                            else -> MaterialTheme.colorScheme.secondaryContainer
                        }
                    )
            ) {
                Icon(
                    when (item.isSelected) {
                        true -> Icons.Rounded.Check
                        else -> Icons.Rounded.Person
                    },
                    contentDescription = null,
                    tint = when (item.isSelected) {
                        true -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.secondary
                    },
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(item.data.student.name, style = MaterialTheme.typography.titleMedium)
                Text(item.data.student.nis.let {
                    if (it.isNotBlank()) it
                    else "Tidak ada NIS"
                }, style = MaterialTheme.typography.bodySmall)
                Text(
                    "${item.data.classroom.name} ${item.data.major.name}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}