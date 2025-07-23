package com.rizalanggoro.presensigo.presentation.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Modifier
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen() {
    val viewModel = koinViewModel<AuthViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    val navController = LocalNavController.current

    LaunchedEffect(uiState.detail) {
        if (uiState.detail is AuthState.Detail.Success) {
            navController.navigate(Routes.Home) {
                popUpTo<Routes.Auth>()
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
        Column(Modifier.padding(it)) {
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Masukkan Nama") }
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Masukkan Alamat Email") }
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Masukkan Kata Sandi") }
            )

            if (uiState.detail is AuthState.Detail.Loading)
                CircularProgressIndicator()

            Button(onClick = { viewModel.test() }) {
                Text("test koin")
            }

            Button(onClick = { viewModel.login(email, password) }) {
                Text("Masuk")
            }
        }
    }
}