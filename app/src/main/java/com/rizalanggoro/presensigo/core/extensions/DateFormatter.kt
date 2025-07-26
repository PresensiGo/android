package com.rizalanggoro.presensigo.core.extensions

import android.os.Build
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
