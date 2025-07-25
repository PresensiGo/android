package com.rizalanggoro.presensigo.domain

import com.rizalanggoro.presensigo.openapi.models.DtoClassroom

data class Classroom(
    val id: Int = 0,
    val name: String = "",
    val majorId: Int = 0
)

fun DtoClassroom.toDomain() = Classroom(
    id = this.id,
    name = this.name,
    majorId = this.majorId
)