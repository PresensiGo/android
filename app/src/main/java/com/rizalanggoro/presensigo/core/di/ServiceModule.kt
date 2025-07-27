package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.BuildConfig
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.domain.Token
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.apis.AuthApi
import com.rizalanggoro.presensigo.openapi.apis.BatchApi
import com.rizalanggoro.presensigo.openapi.apis.ClassroomApi
import com.rizalanggoro.presensigo.openapi.apis.ExcelApi
import com.rizalanggoro.presensigo.openapi.apis.MajorApi
import com.rizalanggoro.presensigo.openapi.apis.ResetApi
import com.rizalanggoro.presensigo.openapi.apis.StudentApi
import com.rizalanggoro.presensigo.openapi.models.RequestsRefreshToken
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import org.koin.dsl.module

private const val API_BASE_URL = BuildConfig.API_BASE_URL

val serviceModule = module {
    single<((HttpClientConfig<*>) -> Unit)> {
        {
            it.install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            it.install(Auth) {
                bearer {
                    loadTokens {
                        val tokenManager = get<TokenManager>()
                        val token = tokenManager.get()

                        BearerTokens(
                            accessToken = token.accessToken,
                            refreshToken = token.refreshToken
                        )
                    }

                    refreshTokens {
                        val tokenManager = get<TokenManager>()
                        val oldToken = tokenManager.get()

                        val client = get<AuthApi>()
                        val response = client.refreshToken(
                            RequestsRefreshToken(
                                refreshToken = oldToken.refreshToken
                            )
                        )

                        if (response.success) {
                            val body = response.body()
                            tokenManager.set(
                                Token(
                                    accessToken = body.accessToken,
                                    refreshToken = body.refreshToken
                                )
                            )
                        }

                        val newToken = tokenManager.get()

                        BearerTokens(
                            accessToken = newToken.accessToken,
                            refreshToken = newToken.refreshToken
                        )
                    }
                }
            }
        }
    }
    single { AuthApi(API_BASE_URL, httpClientConfig = get()) }
    single { AttendanceApi(API_BASE_URL, httpClientConfig = get()) }
    single { BatchApi(API_BASE_URL, httpClientConfig = get()) }
    single { ClassroomApi(API_BASE_URL, httpClientConfig = get()) }
    single { ExcelApi(API_BASE_URL, httpClientConfig = get()) }
    single { MajorApi(API_BASE_URL, httpClientConfig = get()) }
    single { ResetApi(API_BASE_URL, httpClientConfig = get()) }
    single { StudentApi(API_BASE_URL, httpClientConfig = get()) }
}