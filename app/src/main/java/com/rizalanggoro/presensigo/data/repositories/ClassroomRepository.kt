package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.domain.Classroom
import com.rizalanggoro.presensigo.openapi.apis.ClassApi

class ClassroomRepository(
    private val classApi: ClassApi
) {
    suspend fun getAll(majorId: Int): Either<List<Classroom>, Error> = try {
        val response = classApi.getAllClasses()
        when (response.success) {
            true -> Either.Left(response.body().classes.map {
                Classroom(
                    id = it.id,
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