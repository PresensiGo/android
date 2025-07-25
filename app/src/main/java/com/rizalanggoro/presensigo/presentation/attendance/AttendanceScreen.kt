package com.rizalanggoro.presensigo.presentation.attendance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen() {
    val viewModel = koinViewModel<AttendanceViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Lucide.ArrowLeft, contentDescription = null)
                    }
                },
                title = { Text("Attendances") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Lucide.Plus, contentDescription = null)
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text("hello world")
        }
    }
}