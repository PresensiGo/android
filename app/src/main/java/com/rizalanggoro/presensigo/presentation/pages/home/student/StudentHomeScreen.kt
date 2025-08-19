package com.rizalanggoro.presensigo.presentation.pages.home.student

import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.extensions.formatDateTime
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomeScreen() {
    val viewModel = koinViewModel<StudentHomeViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current
    val currentBackStack by navController.currentBackStackEntryAsState()

    LaunchedEffect(currentBackStack?.savedStateHandle) {
        val qrCode = currentBackStack?.savedStateHandle?.get<String>("qrcode")
        if (qrCode != null && qrCode.isNotEmpty()) {
            viewModel.processAttendance(qrCode)
            currentBackStack?.savedStateHandle?.remove<String>("qrcode")
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.QrScanner)
                }
            ) {
                Icon(Icons.Rounded.QrCodeScanner, contentDescription = null)
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            // app bar
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "PresensiGo",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        Calendar.getInstance().timeInMillis.formatDateTime("EEEE, d MMMM yyyy"),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                IconButton(
                    onClick = { navController.navigate(Routes.ProfileStudent) },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background.copy(alpha = .16f)),
                ) {
                    Icon(
                        Icons.Rounded.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            }

            // content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                    .background(MaterialTheme.colorScheme.background),
            ) {

            }
        }
    }
}