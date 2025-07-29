package com.rizalanggoro.presensigo.presentation.lateness.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.domain.combined.StudentMajorClassroom
import com.rizalanggoro.presensigo.ui.theme.CardCornerShape
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailLatenessScreen() {
    val viewModel = koinViewModel<DetailLatenessViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                        }
                    },
                    title = { Text("Detail Keterlambatan") },
                    actions = {
                        IconButton(onClick = { viewModel.getLateness() }) {
                            Icon(Icons.Rounded.Refresh, contentDescription = null)
                        }
                    }
                )
                if (state.status.isLoading())
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Routes.Lateness.Detail.Create(
                            latenessId = viewModel.params.latenessId
                        )
                    )
                }
            ) {
                Icon(Icons.Rounded.PersonAdd, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(state.items) { index, item ->
                StudentItem(
                    index = index,
                    totalCount = state.items.size,
                    item = item
                )
            }
            item {
                Spacer(modifier = Modifier.height((56 + 32).dp))
            }
        }
    }
}

@Composable
fun StudentItem(index: Int, totalCount: Int, item: StudentMajorClassroom) {
    val shape = CardCornerShape.getShape(CardCornerShape.getPosition(index, totalCount))

    ElevatedCard(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .padding(horizontal = 16.dp)
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
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Icon(
                    Icons.Rounded.Person,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column {
                Text(item.student.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    "${item.student.nis} - ${item.major.name} ${item.classroom.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}