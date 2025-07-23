package com.rizalanggoro.presensigo.presentation.home

import com.rizalanggoro.presensigo.domain.Batch

data class HomeState(
    val detail: Detail = Detail.Initial
) {
    sealed interface Detail {
        data object Initial : Detail
        data object Loading : Detail
        data class Success(
            val batches: List<Batch>
        ) : Detail

        data object Failure : Detail
    }
}