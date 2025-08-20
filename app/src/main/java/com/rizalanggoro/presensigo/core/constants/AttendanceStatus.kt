package com.rizalanggoro.presensigo.core.constants

import com.rizalanggoro.presensigo.openapi.models.ConstantsAttendanceStatus

enum class AppAttendanceStatus {
    Present,
    Late,
    Sick,
    Permission,
    Alpha
}

fun AppAttendanceStatus.toConstantsAttendanceStatus(): ConstantsAttendanceStatus = when (this) {
    AppAttendanceStatus.Present -> ConstantsAttendanceStatus.AttendanceStatusPresent
    AppAttendanceStatus.Late -> ConstantsAttendanceStatus.AttendanceStatusPresent
    AppAttendanceStatus.Sick -> ConstantsAttendanceStatus.AttendanceStatusSick
    AppAttendanceStatus.Permission -> ConstantsAttendanceStatus.AttendanceStatusPermission
    AppAttendanceStatus.Alpha -> ConstantsAttendanceStatus.AttendanceStatusAlpha
}

data class AttendanceStatus(
    val title: String,
    val status: ConstantsAttendanceStatus,
    val appStatus: AppAttendanceStatus
)

val attendanceStatuses = listOf<AttendanceStatus>(
    AttendanceStatus(
        "Hadir tepat waktu",
        ConstantsAttendanceStatus.AttendanceStatusPresent,
        AppAttendanceStatus.Present
    ),
    AttendanceStatus(
        "Hadir terlambat",
        ConstantsAttendanceStatus.AttendanceStatusPresent,
        AppAttendanceStatus.Late
    ),
    AttendanceStatus(
        "Sakit",
        ConstantsAttendanceStatus.AttendanceStatusSick,
        AppAttendanceStatus.Sick
    ),
    AttendanceStatus(
        "Izin",
        ConstantsAttendanceStatus.AttendanceStatusPermission,
        AppAttendanceStatus.Permission
    ),
//    AttendanceStatus(
//        "Alpha",
//        ConstantsAttendanceStatus.AttendanceStatusAlpha,
//        AppAttendanceStatus.Alpha
//    )
)
