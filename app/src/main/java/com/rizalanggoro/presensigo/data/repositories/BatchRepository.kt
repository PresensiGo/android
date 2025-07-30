package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.domain.combined.BatchInfo
import com.rizalanggoro.presensigo.domain.toDomain
import com.rizalanggoro.presensigo.openapi.apis.BatchApi

class BatchRepository(
    private val batchApi: BatchApi
) {
    suspend fun getAll(): Either<List<BatchInfo>, Error> = try {
        val response = batchApi.getAllBatches()
        when (response.success) {
            true -> Either.Left(response.body().batches.map {
                BatchInfo(
                    batch = it.batch.toDomain(),
                    majorsCount = it.majorsCount,
                    classroomsCount = it.classroomsCount
                )
            })

            false -> Either.Right(Error("something went wrong!"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Right(Error(e.message))
    }
}