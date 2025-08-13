package com.rizalanggoro.presensigo.presentation.pages.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isFailure
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.isSuccess
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen() {
    val viewModel = koinViewModel<AuthViewModel>()
    val state by viewModel.state.collectAsState()

    val navController = LocalNavController.current
    val context = LocalContext.current


    var isLoginAsTeacher by remember { mutableStateOf(false) }

    LaunchedEffect(state.status) {
        if (state.status.isSuccess())
            navController.navigate(
                if (isLoginAsTeacher) Routes.Home.Teacher
                else Routes.Home.Student
            ) {
                popUpTo<Routes.Auth> {
                    inclusive = true
                }
            }
        else if (state.status.isFailure())
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
    }

    var nis by remember { mutableStateOf("130603") }
    var schoolCode by remember { mutableStateOf("SMAP001") }
    var email by remember { mutableStateOf("admin@gmail.com") }
    var password by remember { mutableStateOf("admin123") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Autentikasi") }
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    "Selamat datang kembali di PresensiGo",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Masukkan beberapa informasi berikut untuk mulai mengakses semua fitur yang ada",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isLoginAsTeacher) {
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
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    TextField(
                        value = nis,
                        onValueChange = { nis = it },
                        placeholder = { Text("Masukkan Nomor Induk Siswa") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = schoolCode,
                        onValueChange = { schoolCode = it },
                        placeholder = { Text("Masukkan Kode Sekolah") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Button(
                onClick = {
                    if (isLoginAsTeacher) viewModel.loginTeacher(email, password)
                    else viewModel.loginStudent(nis = nis, schoolCode = schoolCode)
                },
                enabled = !state.status.isLoading(),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 16.dp),
                contentPadding = when (state.status.isLoading()) {
                    true -> ButtonDefaults.ButtonWithIconContentPadding
                    else -> ButtonDefaults.ContentPadding
                }
            ) {
                if (state.status.isLoading()) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        strokeWidth = 3.dp
                    )
                    Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                }
                Text("Masuk")
            }

            TextButton(
                onClick = { isLoginAsTeacher = !isLoginAsTeacher }
            ) {
                Text(
                    if (isLoginAsTeacher) "Masuk sebagai siswa"
                    else "Masuk sebagai guru"
                )
            }
        }
    }
}