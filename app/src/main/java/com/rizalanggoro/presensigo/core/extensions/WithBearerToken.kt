package com.rizalanggoro.presensigo.core.extensions

import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.openapi.infrastructure.ApiClient

fun <T : ApiClient> T.withBearerToken(tokenManager: TokenManager): T {
    val token = tokenManager.get()
    this.setBearerToken(token.accessToken)
    return this
}