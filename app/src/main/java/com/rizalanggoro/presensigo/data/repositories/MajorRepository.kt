package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.domain.Major
import com.rizalanggoro.presensigo.openapi.apis.MajorApi

class MajorRepository(
    private val majorApi: MajorApi
) {
    suspend fun getAll(batchId: Int): Either<List<Major>, Error> = try {
//        val response = majorApi.getAllMajors(batchId)
//        when (response.success) {
//            true -> {
//                Either.Left(response.body().majors.map {
//                    Major(
//                        id = it.id,
//                        name = it.name
//                    )
//                })
//            }
//
//            false ->
//        }
        Either.Right(Error("something went wrong!"))
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Right(Error(e.message))
    }
}