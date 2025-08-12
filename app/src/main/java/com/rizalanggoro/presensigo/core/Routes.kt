package com.rizalanggoro.presensigo.core

import kotlinx.serialization.Serializable

@Serializable
object Routes {
    @Serializable
    object Auth

    @Serializable
    data object Attendance {
        @Serializable
        data class Create(
            val classroomID: Int
        )

        @Serializable
        data class List(
            val classroomID: Int
        )

        @Serializable
        data class Detail(
            val attendanceId: Int
        )
    }

    @Serializable
    object Home {
        @Serializable
        object Attendance

        @Serializable
        object Lateness

        @Serializable
        object Setting
    }

    @Serializable
    object StudentHome

    @Serializable
    object QrScanner

    @Serializable
    data class Classroom(
        val batchId: Int
    )

    @Serializable
    data class Student(
        val classroomId: Int
    )

    @Serializable
    object Lateness {
        @Serializable
        object Detail {
            @Serializable
            data class Index(
                val latenessId: Int
            )

            @Serializable
            data class Create(
                val latenessId: Int
            )
        }
    }
}