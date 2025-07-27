package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.openapi.apis.ResetApi

class ResetRepository(
    private val resetApi: ResetApi
) {
    suspend fun reset(): Either<Unit, Error> = try {
        val response = resetApi.reset()
        when (response.success) {
            true -> Either.Left(Unit)
            else -> Either.Right(Error("something went wrong"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Right(Error(e.message))
    }
}