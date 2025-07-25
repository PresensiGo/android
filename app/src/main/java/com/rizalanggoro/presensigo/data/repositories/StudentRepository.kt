package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.domain.Student
import com.rizalanggoro.presensigo.openapi.apis.StudentApi

class StudentRepository(
    private val studentApi: StudentApi
) {
    suspend fun getAll(classroomId: Int): Either<List<Student>, Error> = try {
        val response = studentApi.getAllStudents(classId = classroomId)
        when (response.success) {
            true -> Either.Left(response.body().students.map {
                Student(
                    id = it.id,
                    nis = it.nis,
                    name = it.name
                )
            })

            false -> Either.Right(Error("something went wrong"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Right(Error(e.message))
    }
}