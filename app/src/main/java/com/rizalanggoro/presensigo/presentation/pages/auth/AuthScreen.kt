package com.rizalanggoro.presensigo.presentation.pages.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WavingHand
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

    var loginTypeIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(state.status) {
        if (state.status.isSuccess())
            navController.navigate(
                if (loginTypeIndex == 1) Routes.Home.Teacher
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
        containerColor = MaterialTheme.colorScheme.primaryContainer,
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
                Icon(
                    Icons.Rounded.WavingHand,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
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
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    listOf("Siswa", "Guru").mapIndexed { index, item ->
                        val isSelected = index == loginTypeIndex
                        Button(
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                focusedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                disabledElevation = 0.dp
                            ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = when (isSelected) {
                                    true -> MaterialTheme.colorScheme.background
                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                },
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            onClick = { loginTypeIndex = index },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                        ) {
                            Text(item, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
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
                    }
                }

                Button(
                    onClick = {
                        if (loginTypeIndex == 1) viewModel.loginTeacher(email, password)
                        else viewModel.loginStudent(nis = nis, schoolCode = schoolCode)
                    },
                    enabled = !state.status.isLoading(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
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
            }
        }
    }
}