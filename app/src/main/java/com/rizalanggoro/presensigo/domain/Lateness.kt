package com.rizalanggoro.presensigo.domain

import com.rizalanggoro.presensigo.openapi.models.DtoLateness

data class Lateness(
    val id: Int = 0,
    val date: String = "",
    val schoolId: Int = 0
)

fun DtoLateness.toDomain() = Lateness(
    id = id,
    date = date,
    schoolId = schoolId
)
