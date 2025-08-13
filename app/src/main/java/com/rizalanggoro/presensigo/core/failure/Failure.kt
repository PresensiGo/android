package com.rizalanggoro.presensigo.core.failure

import com.google.gson.Gson

data class Failure(
    val message: String
)

fun String.toFailure(): Failure = try {
    Gson().fromJson(this, Failure::class.java)
} catch (e: Exception) {
    e.printStackTrace()
    Failure(message = "Terjadi kesalahan tak terduga!")
}
