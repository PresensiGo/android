package com.rizalanggoro.presensigo.presentation.major

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rizalanggoro.presensigo.core.constants.StateStatus
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MajorScreen() {
    val viewModel = koinViewModel<MajorViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Jurusan")
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            when (uiState.status) {
                StateStatus.Loading -> item {
                    CircularProgressIndicator()
                }

                StateStatus.Success -> items(uiState.majors) {
                    ListItem(headlineContent = {
                        Text(it.name)
                    })
                }

                else -> Unit
            }
        }
    }
}