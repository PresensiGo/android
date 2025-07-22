package com.rizalanggoro.presensigo.presentation.home

data class HomeState(
    val detail: Detail = Detail.Initial
) {
    sealed interface Detail {
        data object Initial : Detail
    }
}