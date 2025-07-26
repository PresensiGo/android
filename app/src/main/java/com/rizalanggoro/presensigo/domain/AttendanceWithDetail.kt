package com.rizalanggoro.presensigo.domain

data class AttendanceWithDetail(
    val attendance: Attendance = Attendance(),
    val items: List<StudentWithAttendanceDetail> = emptyList()
)
