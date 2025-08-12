package com.rizalanggoro.presensigo.core.failure

import com.google.gson.Gson

data class Failure(
    val message: String
)

fun String.toFailure() =
    Gson().fromJson(this, Failure::class.java)