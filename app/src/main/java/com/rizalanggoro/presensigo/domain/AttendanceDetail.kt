package com.rizalanggoro.presensigo.domain

data class AttendanceDetail(
    val id: Int = 0,
    val attendanceId: Int = 0,
    val studentID: Int = 0,
    val status: AttendanceStatus = AttendanceStatus.AttendanceAlpha,
    val note: String = ""
)

//fun DtoAttendanceDetail.toDomain() = AttendanceDetail(
//    id = this.id,
//    attendanceId = this.attendanceId,
//    studentID = this.studentId,
//    status = this.status.toDomain(),
//    note = this.note
//)
