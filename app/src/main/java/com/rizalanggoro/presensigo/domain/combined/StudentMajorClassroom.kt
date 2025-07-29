package com.rizalanggoro.presensigo.domain.combined

import com.rizalanggoro.presensigo.domain.Classroom
import com.rizalanggoro.presensigo.domain.Major
import com.rizalanggoro.presensigo.domain.Student

data class StudentMajorClassroom(
    val student: Student = Student(),
    val major: Major = Major(),
    val classroom: Classroom = Classroom()
)
