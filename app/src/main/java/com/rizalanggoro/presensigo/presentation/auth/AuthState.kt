package com.rizalanggoro.presensigo.presentation.auth

data class AuthState(
    val detail: Detail = Detail.Initial
) {
    sealed interface Detail {
        data object Initial : Detail
        data object Loading : Detail
        data object Success : Detail
        data object Failure : Detail
    }
}