package com.rizalanggoro.presensigo.core

import kotlinx.serialization.Serializable

@Serializable
object Routes {
    @Serializable
    object Auth

    @Serializable
    object Home

    @Serializable
    data class Major(
        val batchId: Int
    )

    @Serializable
    data class Classroom(
        val majorId: Int
    )

    @Serializable
    data class Student(
        val classroomId: Int
    )
}