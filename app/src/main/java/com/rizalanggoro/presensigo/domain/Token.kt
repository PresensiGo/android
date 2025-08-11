package com.rizalanggoro.presensigo.domain

enum class TokenType {
    Teacher, Student
}

data class Token(
    val tokenType: TokenType = TokenType.Teacher,
    val accessToken: String = "",
    val refreshToken: String = ""
)