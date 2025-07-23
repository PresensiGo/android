package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.domain.Batch
import com.rizalanggoro.presensigo.openapi.apis.BatchApi

class BatchRepository(
    private val batchApi: BatchApi
) {
    suspend fun getAll(): Either<List<Batch>, Error> = try {
        val response = batchApi.getAllBatches()
        when (response.success) {
            true -> Either.Left(response.body().batches.map {
                Batch(
                    id = it.id ?: 0,
                    name = it.name ?: ""
                )
            })

            false -> Either.Right(Error("something went wrong!"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Right(Error(e.message))
    }
}