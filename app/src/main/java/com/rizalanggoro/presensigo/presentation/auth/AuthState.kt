package com.rizalanggoro.presensigo.presentation.auth

import com.rizalanggoro.presensigo.core.constants.StateStatus

data class AuthState(
    val status: StateStatus = StateStatus.Initial
)