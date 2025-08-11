package com.rizalanggoro.presensigo.domain

data class Classroom(
    val id: Int = 0,
    val name: String = "",
    val majorId: Int = 0
)

//fun DtoClassroom.toDomain() = Classroom(
//    id = this.id,
//    name = this.name,
//    majorId = this.majorId
//)