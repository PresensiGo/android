package com.rizalanggoro.presensigo.domain

enum class TokenType {
    Unset, Teacher, Student
}

data class Token(
    val tokenType: TokenType = TokenType.Unset,
    val accessToken: String = "",
    val refreshToken: String = ""
)