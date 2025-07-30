package com.rizalanggoro.presensigo.domain.combined

import com.rizalanggoro.presensigo.domain.Batch

data class BatchInfo(
    val batch: Batch,
    val majorsCount: Int,
    val classroomsCount: Int
)
