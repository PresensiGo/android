package com.rizalanggoro.presensigo.core.extensions

import android.os.Build
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun String.formatDateTime(pattern: String = "EEEE, dd MMMM yyyy"): String {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && this.isNotEmpty()) {
//            val isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
            val isoFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
            val zonedDateTime = ZonedDateTime.parse(this, isoFormatter)

            val targetZoneId = ZoneId.systemDefault()
            val zonedDateTimeInWib = zonedDateTime.withZoneSameInstant(targetZoneId)

            val outputFormatter = DateTimeFormatter.ofPattern(
                pattern,
                Locale("id", "ID")
            )

            return zonedDateTimeInWib.format(outputFormatter).toString()
        } else {
            return this
        }
    } catch (_: Exception) {
        return this
    }
}

fun Long.formatDateTime(pattern: String = "EEEE, dd MMMM yyyy"): String = try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val instant = Instant.ofEpochMilli(this)
        val zonedDateTime = instant.atZone(ZoneId.systemDefault())

        val outputFormatter = DateTimeFormatter.ofPattern(
            pattern,
            Locale("id", "ID")
        )

        zonedDateTime.format(outputFormatter).toString()
    } else {
        this.toString()
    }
} catch (_: Exception) {
    this.toString()
}

@Deprecated("use oldFormatDateTime instead")
fun Long.oldFormatDateTime(): String = try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val instant = Instant.ofEpochMilli(this)
        val zonedDateTime = instant.atZone(ZoneId.systemDefault())

        val locale = Locale("id", "ID")
        val dayOfWeek = zonedDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, locale)
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale)

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