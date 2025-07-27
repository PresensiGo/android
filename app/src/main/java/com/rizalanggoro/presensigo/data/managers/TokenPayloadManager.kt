package com.rizalanggoro.presensigo.data.managers

import android.util.Base64
import com.google.gson.Gson
import com.rizalanggoro.presensigo.domain.TokenPayload
import java.nio.charset.Charset

class TokenPayloadManager {
    fun decode(token: String): TokenPayload {
        val parts = token.split(".")
        if (parts.size != 3)
            return TokenPayload()

        return try {
            val payloadBytes = Base64.decode(parts[1], Base64.URL_SAFE)
            val payloadString = String(payloadBytes, Charset.forName("UTF-8"))

            Gson().fromJson(payloadString, TokenPayload::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            TokenPayload()
        }
    }
}