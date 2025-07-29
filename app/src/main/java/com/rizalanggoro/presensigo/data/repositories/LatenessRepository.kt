package com.rizalanggoro.presensigo.data.repositories

import arrow.core.Either
import com.rizalanggoro.presensigo.domain.Lateness
import com.rizalanggoro.presensigo.domain.toDomain
import com.rizalanggoro.presensigo.openapi.apis.LatenessApi
import com.rizalanggoro.presensigo.openapi.models.CreateLatenessDetailReq
import com.rizalanggoro.presensigo.openapi.models.CreateLatenessReq

class LatenessRepository(
    private val latenessApi: LatenessApi
) {
    suspend fun create(date: String): Either<Unit, Error> = try {
        val response = latenessApi.createLateness(
            CreateLatenessReq(
                date = date
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

    suspend fun createDetail(latenessId: Int, studentIds: List<Int>): Either<Unit, Error> = try {
        val response = latenessApi.createLatenessDetail(
            latenessId = latenessId,
            body = CreateLatenessDetailReq(
                studentIds = studentIds
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

    suspend fun getAll(): Either<List<Lateness>, Error> = try {
        val response = latenessApi.getAllLatenesses()
        when (response.success) {
            true -> Either.Left(response.body().latenesses.map { it.toDomain() })

            else -> Either.Right(Error("something went wrong"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Right(Error(e.message))
    }
}