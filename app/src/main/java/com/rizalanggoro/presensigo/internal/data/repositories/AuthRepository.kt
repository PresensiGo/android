package com.rizalanggoro.presensigo.internal.data.repositories

import android.util.Log
import com.rizalanggoro.presensigo.openapi.apis.AuthApi
import com.rizalanggoro.presensigo.openapi.models.AuthLoginRequest

class AuthRepository(
    private val authApi: AuthApi
) {
    companion object {
        private const val TAG = "AuthRepository"
    }

    suspend fun login(email: String, password: String) = try {
        val response = authApi.apiV1AuthLoginPost(
            AuthLoginRequest(
                email, password
            )
        )
        when (response.success) {
            true -> {
                Log.d(TAG, "login: success")
            }

            false -> {
                Log.d(TAG, "login: fail")
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    suspend fun register() {}

    suspend fun logout() {}

    suspend fun refreshToken() {}
}