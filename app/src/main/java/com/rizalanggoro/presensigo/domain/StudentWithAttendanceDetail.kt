package com.rizalanggoro.presensigo.domain

data class StudentWithAttendanceDetail(
    val student: Student = Student(),
    val attendanceDetail: AttendanceDetail = AttendanceDetail()
)