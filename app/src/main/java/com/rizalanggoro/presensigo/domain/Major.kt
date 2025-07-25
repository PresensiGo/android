package com.rizalanggoro.presensigo.domain

import com.rizalanggoro.presensigo.openapi.models.DtoMajor

data class Major(
    val id: Int = 0,
    val name: String = ""
)

fun DtoMajor.toDomain() = Major(
    id = this.id,
    name = this.name
)