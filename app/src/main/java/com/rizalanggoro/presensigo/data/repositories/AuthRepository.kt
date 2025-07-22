package com.rizalanggoro.presensigo.data.repositories

import android.util.Log
import com.rizalanggoro.presensigo.openapi.apis.AuthApi
import com.rizalanggoro.presensigo.openapi.models.RequestsLoginRequest
import com.rizalanggoro.presensigo.openapi.models.RequestsRegisterRequest

class AuthRepository(
    private val authApi: AuthApi
) {
    companion object {
        private const val TAG = "AuthRepository"
    }

    suspend fun login(email: String, password: String) = try {
        val response = authApi.login(
            RequestsLoginRequest(
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

    suspend fun register(name: String, email: String, password: String) = try {
        val response = authApi.register(
            RequestsRegisterRequest(
                name = name, email = email, password = password
            )
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }

    suspend fun logout() {}

    suspend fun refreshToken() {}
}