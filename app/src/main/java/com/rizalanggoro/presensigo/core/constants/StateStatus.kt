package com.rizalanggoro.presensigo.core.constants

@Deprecated("use ui state instead")
enum class StateStatus {
    Initial,
    Loading,
    Success,
    Failure
}

fun StateStatus.isLoading() = this == StateStatus.Loading
fun StateStatus.isSuccess() = this == StateStatus.Success
fun StateStatus.isFailure() = this == StateStatus.Failure