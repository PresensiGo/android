package com.rizalanggoro.presensigo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rizalanggoro.presensigo.core.application.MyComposeApplication
import com.rizalanggoro.presensigo.data.managers.TokenManager
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val tokenManager by inject<TokenManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val isAuthenticated = !tokenManager.isEmpty()

        setContent {
            MyComposeApplication(
                isAuthenticated = isAuthenticated
            )
        }
    }
}
