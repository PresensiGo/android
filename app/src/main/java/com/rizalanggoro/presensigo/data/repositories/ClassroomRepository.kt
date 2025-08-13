package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.domain.ClassroomMajor
import com.rizalanggoro.presensigo.openapi.apis.ClassroomApi

class ClassroomRepository(
    private val classroomApi: ClassroomApi
) {
    suspend fun getAllWithMajors(batchId: Int): Either<List<ClassroomMajor>, Error> = try {
//        val response = classroomApi.getAllClassroomWithMajors(batchId = batchId)
//        when (response.success) {
//            true -> {
//                val body = response.body()
//                Either.Left(body.data.map {
//                    ClassroomMajor(
//                        classroom = it.classroom.toDomain(),
//                        major = it.major.toDomain()
//                    )
//                })
//            }
//
//            false ->
//        }
        Either.Right(Error("something went wrong"))
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Right(Error(e.message))
    }
}