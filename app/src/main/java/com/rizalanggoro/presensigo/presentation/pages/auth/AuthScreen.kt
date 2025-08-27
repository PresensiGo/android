package com.rizalanggoro.presensigo.presentation.pages.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.core.constants.isLoading
import com.rizalanggoro.presensigo.core.constants.onFailure
import com.rizalanggoro.presensigo.core.constants.onSuccess
import com.rizalanggoro.presensigo.presentation.components.CustomTab
import com.rizalanggoro.presensigo.presentation.components.PrimaryButton
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AuthScreen() {
    val viewModel = koinViewModel<AuthViewModel>()
    val login by viewModel.loginState.collectAsState()

    val navController = LocalNavController.current
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    var loginTypeIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(login) {
        login
            .onSuccess {
                navController.navigate(
                    if (loginTypeIndex == 1) Routes.Home.Teacher
                    else Routes.Home.Student
                ) {
                    popUpTo<Routes.Auth> {
                        inclusive = true
                    }
                }
            }
            .onFailure {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = it
                    )
                }
                viewModel.resetLoginState()
            }
    }

    var nis by remember { mutableStateOf("") }
    var schoolCode by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                LoadingIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(40.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        "Selamat datang di Aplikasi PresensiGo",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        "Silahkan masukkan beberapa informasi berikut untuk mulai mengakses semua fitur yang ada",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .8f)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomTab(
                    selectedIndex = loginTypeIndex,
                    items = listOf("Siswa", "Guru"),
                    onItemSelected = {
                        loginTypeIndex = it
                    },
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (loginTypeIndex == 1) {
                        Text(
                            "Alamat Email",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        OutlinedTextField(
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                            ),
                            shape = RoundedCornerShape(8.dp),
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text("Masukkan alamat email") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            "Kata Sandi",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        OutlinedTextField(
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                            ),
                            shape = RoundedCornerShape(8.dp),
                            value = password,
                            onValueChange = { password = it },
                            placeholder = { Text("Masukkan kata sandi") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Text(
                            "Nomor Induk Siswa",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        OutlinedTextField(
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                            ),
                            shape = RoundedCornerShape(8.dp),
                            value = nis,
                            onValueChange = { nis = it },
                            placeholder = { Text("Masukkan nomor induk siswa") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            "Kode Sekolah",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        OutlinedTextField(
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                            ),
                            shape = RoundedCornerShape(8.dp),
                            value = schoolCode,
                            onValueChange = { schoolCode = it },
                            placeholder = { Text("Masukkan kode sekolah") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            "Perhatian! Setelah Anda menekan tombol masuk, perangkat akan ditautkan " +
                                    "dengan NIS Anda sehingga perangkat ini tidak bisa digunakan untuk masuk " +
                                    "menggunakan akun lainnya.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)
                        )
                    }
                }

                PrimaryButton(
                    text = "Masuk",
                    isLoading = login.isLoading(),
                    onClick = {
                        if (loginTypeIndex == 1) viewModel.loginTeacher(email, password)
                        else viewModel.loginStudent(nis = nis, schoolCode = schoolCode)
                    }
                )
            }
        }
    }
}