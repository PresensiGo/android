package com.rizalanggoro.presensigo.core.constants

sealed class UiState<out T> {
    object Initial : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val message: String = "Terjadi kesalahan tak terduga!") : UiState<Nothing>()
}

fun UiState<*>.isLoading() = this is UiState.Loading
fun UiState<*>.isSuccess() = this is UiState.Success
fun UiState<*>.isFailure() = this is UiState.Failure