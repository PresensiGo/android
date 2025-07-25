package com.rizalanggoro.presensigo.domain

data class AttendanceStudent(
    val studentID: Int = 0,
    val status: AttendanceStatus = AttendanceStatus.AttendanceAlpha,
    val note: String = ""
)
