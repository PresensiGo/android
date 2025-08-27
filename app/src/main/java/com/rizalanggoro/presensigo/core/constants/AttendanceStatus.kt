package com.rizalanggoro.presensigo.core.constants

import com.rizalanggoro.presensigo.openapi.models.ConstantsAttendanceStatusType

enum class AppAttendanceStatus {
    Present,
    Late,
    Sick,
    Permission,
    Alpha
}


data class AttendanceStatus(
    val title: String,
    val statusType: ConstantsAttendanceStatusType
)

val attendanceStatuses = listOf<AttendanceStatus>(
    AttendanceStatus(
        "Hadir tepat waktu",
        ConstantsAttendanceStatusType.AttendanceStatusTypePresentOnTime
    ),
    AttendanceStatus(
        "Hadir waktu saat ini",
        ConstantsAttendanceStatusType.AttendanceStatusTypePresentLate
    ),
    AttendanceStatus(
        "Sakit",
        ConstantsAttendanceStatusType.AttendanceStatusTypeSick
    ),
    AttendanceStatus(
        "Izin",
        ConstantsAttendanceStatusType.AttendanceStatusTypePermission
    ),
)
