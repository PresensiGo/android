package com.rizalanggoro.presensigo.domain

import com.rizalanggoro.presensigo.openapi.models.DtoAttendance

data class Attendance(
    val id: Int = 0,
    val date: String = "",
    val classroomId: Int = 0
)

fun DtoAttendance.toDomain() = Attendance(
    id = this.id,
    date = this.date,
    classroomId = this.classroomId
)
