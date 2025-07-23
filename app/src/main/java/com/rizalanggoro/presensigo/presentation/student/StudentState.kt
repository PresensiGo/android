package com.rizalanggoro.presensigo.presentation.student

import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.domain.Student

data class StudentState(
    val status: StateStatus = StateStatus.Initial,
    val students: List<Student> = emptyList()
)
