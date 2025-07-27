package com.rizalanggoro.presensigo.domain

import com.google.gson.annotations.SerializedName

data class TokenPayload(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val role: String = "",
    @SerializedName("school_id")
    val schoolId: Int = 0,
    @SerializedName("school_name")
    val schoolName: String = "",
    @SerializedName("school_code")
    val schoolCode: String = ""
)
