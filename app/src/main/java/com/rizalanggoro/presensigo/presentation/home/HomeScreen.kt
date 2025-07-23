package com.rizalanggoro.presensigo.presentation.home

import androidx.compose.foundation.clickable
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
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("PresensiGo")
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            with(uiState.detail) {

                when (this) {
                    is HomeState.Detail.Loading ->
                        item {
                            CircularProgressIndicator()
                        }

                    is HomeState.Detail.Success -> {
                        items(batches) {
                            ListItem(
                                headlineContent = {
                                    Text(it.name)
                                },
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        Routes.Major(
                                            batchId = it.id
                                        )
                                    )
                                }
                            )
                        }
                    }

                    else -> Unit
                }
            }
        }
    }
}