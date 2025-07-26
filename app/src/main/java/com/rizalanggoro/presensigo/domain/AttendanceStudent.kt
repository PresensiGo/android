package com.rizalanggoro.presensigo.domain

import com.rizalanggoro.presensigo.openapi.models.DtoAttendanceStudent

data class AttendanceStudent(
    val id: Int = 0,
    val attendanceId: Int = 0,
    val studentID: Int = 0,
    val status: AttendanceStatus = AttendanceStatus.AttendanceAlpha,
    val note: String = ""
)

fun DtoAttendanceStudent.toDomain() = AttendanceStudent(
    id = this.id,
    attendanceId = this.attendanceId,
    studentID = this.studentId,
    status = this.status.toDomain(),
    note = this.note
)
