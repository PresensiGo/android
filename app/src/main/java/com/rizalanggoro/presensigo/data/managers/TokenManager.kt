package com.rizalanggoro.presensigo.data.managers

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.rizalanggoro.presensigo.domain.Token

class TokenManager(
    context: Context
) {
    private val gson = Gson()
    private val preferences = context.getSharedPreferences("token_manager", Context.MODE_PRIVATE)

    fun isEmpty(): Boolean = preferences.getString("token", null) == null

    fun set(token: Token) {
        val json = gson.toJson(token)
        preferences.edit { putString("token", json) }
    }

    fun get(): Token {
        val json = preferences.getString("token", null)
        if (json == null) return Token()
        return gson.fromJson(json, Token::class.java)
    }
}