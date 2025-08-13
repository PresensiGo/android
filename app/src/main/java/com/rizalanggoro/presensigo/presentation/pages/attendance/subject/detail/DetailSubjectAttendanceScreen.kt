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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSubjectAttendanceScreen() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(1) }

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
                listOf("Kode Akses", "Daftar Siswa", "Lainnya").mapIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index },
                        text = { Text(item) }
                    )
                }
            }

            // content
            when (selectedTabIndex) {
                0 -> Section1()
                1 -> Section2()
                2 -> Section3()
            }
        }
    }
}