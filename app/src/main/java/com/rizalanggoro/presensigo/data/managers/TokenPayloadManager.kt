package com.rizalanggoro.presensigo.data.managers

import android.util.Base64
import com.google.gson.Gson
import com.rizalanggoro.presensigo.domain.StudentTokenPayload
import com.rizalanggoro.presensigo.domain.TeacherTokenPayload
import java.nio.charset.Charset

class TokenPayloadManager {
    fun decodeTeacher(token: String): TeacherTokenPayload {
        val parts = token.split(".")
        if (parts.size != 3)
            return TeacherTokenPayload()

        return try {
            val payloadBytes = Base64.decode(parts[1], Base64.URL_SAFE)
            val payloadString = String(payloadBytes, Charset.forName("UTF-8"))

            Gson().fromJson(payloadString, TeacherTokenPayload::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            TeacherTokenPayload()
        }
    }

    fun decodeStudent(token: String): StudentTokenPayload {
        val parts = token.split(".")
        if (parts.size != 3)
            return StudentTokenPayload()

        return try {
            val payloadBytes = Base64.decode(parts[1], Base64.URL_SAFE)
            val payloadString = String(payloadBytes, Charset.forName("UTF-8"))

            Gson().fromJson(payloadString, StudentTokenPayload::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            StudentTokenPayload()
        }
    }
}