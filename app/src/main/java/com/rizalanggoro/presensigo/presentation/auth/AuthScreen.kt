package com.rizalanggoro.presensigo.presentation.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import com.rizalanggoro.presensigo.presentation.components.SmallCircularProgressIndicator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen() {
    val viewModel = koinViewModel<AuthViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current

    LaunchedEffect(state.status) {
        if (state.status.isSuccess())
            navController.navigate(Routes.Home) {
                popUpTo<Routes.Auth> {
                    inclusive = true
                }
            }
    }

    var name by remember { mutableStateOf("Rizal Dwi Anggoro") }
    var email by remember { mutableStateOf("rizal@email.com") }
    var password by remember { mutableStateOf("password") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Authentication") })
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (false)
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Masukkan Nama") }
                )
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Masukkan Alamat Email") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Masukkan Kata Sandi") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            Button(
                onClick = { viewModel.login(email, password) },
                enabled = !state.status.isLoading(),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp)
            ) {
                if (state.status.isLoading()) SmallCircularProgressIndicator()
                else Text("Masuk")
            }
        }
    }
}