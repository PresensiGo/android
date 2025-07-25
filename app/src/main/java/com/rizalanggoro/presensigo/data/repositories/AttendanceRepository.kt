package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.domain.Attendance
import com.rizalanggoro.presensigo.domain.AttendanceStudent
import com.rizalanggoro.presensigo.domain.toDTO
import com.rizalanggoro.presensigo.domain.toDomain
import com.rizalanggoro.presensigo.openapi.apis.AttendanceApi
import com.rizalanggoro.presensigo.openapi.models.CreateAttendanceItemReq
import com.rizalanggoro.presensigo.openapi.models.CreateAttendanceReq

class AttendanceRepository(
    private val attendanceApi: AttendanceApi
) {
    suspend fun create(
        attendance: Attendance,
        attendanceStudents: List<AttendanceStudent>
    ): Either<Unit, Error> = try {
        val response = attendanceApi.createAttendance(
            CreateAttendanceReq(
                date = attendance.date,
                classroomId = attendance.classroomId,
                attendanceStudents = attendanceStudents.map {
                    CreateAttendanceItemReq(
                        studentId = it.studentID,
                        status = it.status.toDTO(),
                        note = it.note
                    )
                }
            )
        )
        when (response.success) {
            true -> Either.Left(Unit)
            else -> Either.Right(Error("something went wrong"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Right(Error(e.message))
    }

    suspend fun getAll(classroomID: Int): Either<List<Attendance>, Error> = try {
        val response = attendanceApi.getAllAttendances(classroomID)
        when (response.success) {
            true -> Either.Left(response.body().attendances.map { it.toDomain() })
            false -> Either.Right(Error("Something went wrong"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Right(Error(e.message))
    }
}