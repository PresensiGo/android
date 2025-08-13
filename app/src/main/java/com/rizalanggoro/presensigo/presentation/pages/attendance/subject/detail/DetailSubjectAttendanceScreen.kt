package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections.Section1
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections.Section2
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections.Section3

private data class Destination(
    val title: String,
    val content: @Composable () -> Unit
)

private val destinations = listOf(
    Destination(title = "Kode Akses", content = { Section1() }),
    Destination(title = "Daftar Siswa", content = { Section2() }),
    Destination(title = "Lainnya", content = { Section3() }),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSubjectAttendanceScreen() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Presensi") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
        ) {
            PrimaryTabRow(
                selectedTabIndex = selectedTabIndex
            ) {
                destinations.mapIndexed { index, item ->
                    Tab(
                        selected = false,
                        onClick = { selectedTabIndex = index },
                        text = { Text(item.title) }
                    )
                }
            }
            destinations[selectedTabIndex].content()
        }
    }
}