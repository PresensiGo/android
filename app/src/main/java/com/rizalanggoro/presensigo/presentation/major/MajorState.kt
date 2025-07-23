package com.rizalanggoro.presensigo.presentation.major

import com.rizalanggoro.presensigo.core.constants.StateStatus
import com.rizalanggoro.presensigo.domain.Major

data class MajorState(
    val status: StateStatus = StateStatus.Initial,
    val majors: List<Major> = emptyList()
)
