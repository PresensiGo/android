package com.rizalanggoro.presensigo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.rizalanggoro.presensigo.core.application.MyComposeApplication
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.data.repositories.AuthRepository
import com.rizalanggoro.presensigo.domain.TokenType
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val tokenManager by inject<TokenManager>()
    private val authRepository by inject<AuthRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val isAuthenticated = !tokenManager.isEmpty()
        if (isAuthenticated) refreshTokenTTL()

        setContent {
            MyComposeApplication(
                isAuthenticated = isAuthenticated,
                tokenType = when (isAuthenticated) {
                    true -> tokenManager.get().tokenType
                    else -> TokenType.Unset
                }
            )
        }
    }

    private fun refreshTokenTTL() = lifecycleScope.launch {
        authRepository.refreshTokenTTL()
    }
}
