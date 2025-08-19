package com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.presentation.components.CustomTab
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections.Section1
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections.Section2
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.sections.Section3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSubjectAttendanceScreen() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(1) }

    val navController = LocalNavController.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Column(
            modifier = Modifier.padding(it),
        ) {
            // app bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                }
                Text(
                    "Detail Presensi",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                    .background(MaterialTheme.colorScheme.background),
            ) {
                CustomTab(
                    items = listOf("QR", "Siswa", "Lainnya"),
                    selectedIndex = selectedTabIndex,
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)
                ) { selectedTabIndex = it }

                // content
                when (selectedTabIndex) {
                    0 -> Section1()
                    1 -> Section2()
                    2 -> Section3()
                }
            }
        }
    }
}