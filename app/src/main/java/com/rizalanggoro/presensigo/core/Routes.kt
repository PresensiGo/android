package com.rizalanggoro.presensigo.core

import kotlinx.serialization.Serializable

@Serializable
object Routes {
    @Serializable
    object Auth

    @Serializable
    data object Attendance {
        @Serializable
        object General {
            @Serializable
            data class Detail(
                val attendanceId: Int
            )

            @Serializable
            data object Create
        }

        @Serializable
        data object Subject {
            @Serializable
            data class Major(
                val batchId: Int
            )

            @Serializable
            data class Classroom(
                val batchId: Int,
                val majorId: Int
            )

            @Serializable
            data class Index(
                val batchId: Int,
                val majorId: Int,
                val classroomId: Int
            )

            @Serializable
            data class Detail(
                val batchId: Int,
                val majorId: Int,
                val classroomId: Int,
                val attendanceId: Int
            )

            @Serializable
            data class Create(
                val batchId: Int,
                val majorId: Int,
                val classroomId: Int
            )
        }


        // old
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
        data object Teacher {
            @Serializable
            data object SubjectAttendance

            @Serializable
            data object GeneralAttendance

            @Serializable
            data object Setting
        }

        @Serializable
        data object Student {
            @Serializable
            data object SubjectAttendance

            @Serializable
            data object GeneralAttendance

            @Serializable
            data object Setting
        }
    }

    @Serializable
    data object QrScanner

    @Serializable
    data class Classroom(
        val batchId: Int,
        val majorId: Int
    )

    @Serializable
    data object Student {
        @Serializable
        data object Profile
    }

//    @Serializable
//    object StudentHome

//    @Serializable
//    data class Student(
//        val classroomId: Int
//    )

//    @Serializable
//    object Lateness {
//        @Serializable
//        object Detail {
//            @Serializable
//            data class Index(
//                val latenessId: Int
//            )
//
//            @Serializable
//            data class Create(
//                val latenessId: Int
//            )
//        }
//    }
}