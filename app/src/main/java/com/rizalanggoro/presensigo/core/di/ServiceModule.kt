package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.BuildConfig
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.domain.Token
import com.rizalanggoro.presensigo.domain.TokenType
import com.rizalanggoro.presensigo.openapi.apis.AccountApi
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.apis.BatchApi
import com.rizalanggoro.presensigo.openapi.apis.ClassroomApi
import com.rizalanggoro.presensigo.openapi.apis.MajorApi
import com.rizalanggoro.presensigo.openapi.apis.ResetApi
import com.rizalanggoro.presensigo.openapi.apis.StudentApi
import com.rizalanggoro.presensigo.openapi.apis.SubjectApi
import com.rizalanggoro.presensigo.openapi.models.RefreshTokenReq
import com.rizalanggoro.presensigo.openapi.models.RefreshTokenStudentReq
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.ResponseException
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
            it.expectSuccess = true

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

                        try {
                            if (oldToken.tokenType == TokenType.Teacher) {
                                val client = get<AccountApi>()
                                val body = client.refreshToken(
                                    RefreshTokenReq(
                                        refreshToken = oldToken.refreshToken
                                    )
                                ).body()

                                tokenManager.set(
                                    Token(
                                        tokenType = TokenType.Teacher,
                                        accessToken = body.accessToken,
                                        refreshToken = body.refreshToken
                                    )
                                )

                                BearerTokens(
                                    accessToken = body.accessToken,
                                    refreshToken = body.refreshToken
                                )
                            } else {
                                val client = get<StudentApi>()
                                val body = client.refreshTokenStudent(
                                    RefreshTokenStudentReq(
                                        refreshToken = oldToken.refreshToken
                                    )
                                ).body()

                                tokenManager.set(
                                    Token(
                                        tokenType = TokenType.Student,
                                        accessToken = body.accessToken,
                                        refreshToken = body.refreshToken
                                    )
                                )

                                BearerTokens(
                                    accessToken = body.accessToken,
                                    refreshToken = body.refreshToken
                                )
                            }
                        } catch (e: ResponseException) {
                            e.printStackTrace()
                            tokenManager.clear()
                            null
                        }
                    }
                }
            }
        }
    }
//    single { AuthApi(API_BASE_URL, httpClientConfig = get()) }
    single { AccountApi(API_BASE_URL, httpClientConfig = get()) }
    single { AttendanceApi(API_BASE_URL, httpClientConfig = get()) }
    single { BatchApi(API_BASE_URL, httpClientConfig = get()) }
    single { ClassroomApi(API_BASE_URL, httpClientConfig = get()) }
//    single { ExcelApi(API_BASE_URL, httpClientConfig = get()) }
//    single { LatenessApi(API_BASE_URL, httpClientConfig = get()) }
    single { MajorApi(API_BASE_URL, httpClientConfig = get()) }
    single { ResetApi(API_BASE_URL, httpClientConfig = get()) }
    single { StudentApi(API_BASE_URL, httpClientConfig = get()) }
    single { SubjectApi(API_BASE_URL, httpClientConfig = get()) }
}