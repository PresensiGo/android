package com.rizalanggoro.presensigo.presentation.attendance

import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.domain.Attendance

data class AttendanceState(
    val status: StateStatus = StateStatus.Initial,
    val attendances: List<Attendance> = emptyList()
)
