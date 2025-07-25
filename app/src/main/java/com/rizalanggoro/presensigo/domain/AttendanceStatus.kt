package com.rizalanggoro.presensigo.domain

import com.rizalanggoro.presensigo.openapi.models.ModelsAttendanceStatus

enum class AttendanceStatus(
    val title: String,
    val shortTitle: String
) {
    AttendancePresent(title = "Hadir", shortTitle = "H"),
    AttendancePermission(title = "Izin", shortTitle = "I"),
    AttendanceSick(title = "Sakit", shortTitle = "S"),
    AttendanceAlpha(title = "Alpha", shortTitle = "A"),
}

fun AttendanceStatus.toDTO() = when (this) {
    AttendanceStatus.AttendancePresent -> ModelsAttendanceStatus.AttendancePresent
    AttendanceStatus.AttendancePermission -> ModelsAttendanceStatus.AttendancePermission
    AttendanceStatus.AttendanceSick -> ModelsAttendanceStatus.AttendanceSick
    AttendanceStatus.AttendanceAlpha -> ModelsAttendanceStatus.AttendanceAlpha
}

fun ModelsAttendanceStatus.toDomain() = when (this) {
    ModelsAttendanceStatus.AttendancePresent -> AttendanceStatus.AttendancePresent
    ModelsAttendanceStatus.AttendancePermission -> AttendanceStatus.AttendancePermission
    ModelsAttendanceStatus.AttendanceSick -> AttendanceStatus.AttendanceSick
    ModelsAttendanceStatus.AttendanceAlpha -> AttendanceStatus.AttendanceAlpha
}