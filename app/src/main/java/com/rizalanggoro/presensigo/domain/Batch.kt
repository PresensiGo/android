package com.rizalanggoro.presensigo.domain

data class Batch(
    val id: Int = 0,
    val name: String = ""
)

//fun DtoBatch.toDomain() = Batch(
//    id = this.id,
//    name = this.name
//)