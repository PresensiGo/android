package com.rizalanggoro.presensigo.core.extensions

import android.os.Build
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun String.toLocalDateString(): String {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && this.isNotEmpty()) {
            val isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
            val zonedDateTime = ZonedDateTime.parse(this, isoFormatter)

            val dayOfWeek =
                zonedDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("id", "ID"))
            val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

            return "$dayOfWeek, ${zonedDateTime.format(outputFormatter)}"
        } else {
            return this
        }
    } catch (_: Exception) {
        return this
    }
}

fun Long.toLocalDateString(): String = try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val instant = Instant.ofEpochMilli(this)
        val zonedDateTime = instant.atZone(ZoneId.systemDefault())

        val dayOfWeek = zonedDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("id", "ID"))
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

        "$dayOfWeek, ${zonedDateTime.format(outputFormatter)}"
    } else {
        throw Exception("not supported device")
    }
} catch (_: Exception) {
    this.toString()
}

fun Long.toApiFormatString(): String = try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val instant = Instant.ofEpochMilli(this)
        val zonedDateTime = instant.atZone(ZoneId.systemDefault())

        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        zonedDateTime.format(outputFormatter)
    } else {
        throw Exception("not supported device")
    }
} catch (_: Exception) {
    this.toString()
}