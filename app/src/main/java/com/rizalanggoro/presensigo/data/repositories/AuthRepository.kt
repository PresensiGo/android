package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.domain.Token
import com.rizalanggoro.presensigo.openapi.apis.AuthApi
import com.rizalanggoro.presensigo.openapi.models.RequestsLogin
import com.rizalanggoro.presensigo.openapi.models.RequestsRegister

class AuthRepository(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) {
    companion object {
        private const val TAG = "AuthRepository"
    }

    suspend fun login(email: String, password: String): Either<Boolean, Error> = try {
        val response = authApi.login(
            RequestsLogin(
                email = email,
                password = password
            )
        )

        when (response.success) {
            true -> {
                val (token) = response.body()
                tokenManager.set(
                    Token(
                        accessToken = token.accessToken,
                        refreshToken = token.refreshToken
                    )
                )
                Either.Left(true)
            }

            false -> {
                Either.Right(Error("Login failed"))
            }
        }
    } catch (e: Exception) {
        Either.Right(Error(e.message))
    }

    suspend fun register(name: String, email: String, password: String) = try {
        val response = authApi.register(
            RequestsRegister(
                name = name,
                email = email,
                password = password
            )
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }

    suspend fun logout() {}

    suspend fun refreshToken() {}
}