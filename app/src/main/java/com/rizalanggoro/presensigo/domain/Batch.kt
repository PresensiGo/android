package com.rizalanggoro.presensigo.domain

import com.rizalanggoro.presensigo.openapi.models.DtoBatch

data class Batch(
    val id: Int = 0,
    val name: String = ""
)

fun DtoBatch.toDomain() = Batch(
    id = this.id,
    name = this.name
)