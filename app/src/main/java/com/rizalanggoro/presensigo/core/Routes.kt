package com.rizalanggoro.presensigo.core

import kotlinx.serialization.Serializable

@Serializable
object Routes {
    @Serializable
    object Auth

    @Serializable
    object Home

    @Serializable
    data class Classroom(
        val batchId: Int
    )

    @Serializable
    data class Student(
        val classroomId: Int
    )

    @Serializable
    object Setting {}
}