package com.rizalanggoro.presensigo.domain.combined

import com.rizalanggoro.presensigo.domain.Lateness

data class LatenessWithDetail(
    val lateness: Lateness = Lateness(),
    val items: List<StudentMajorClassroom> = emptyList()
)
