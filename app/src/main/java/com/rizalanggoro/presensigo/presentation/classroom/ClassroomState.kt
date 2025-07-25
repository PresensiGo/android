package com.rizalanggoro.presensigo.presentation.classroom

import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.domain.ClassroomMajor

data class ClassroomState(
    val status: StateStatus = StateStatus.Initial,
    val classrooms: List<ClassroomMajor> = emptyList()
)
