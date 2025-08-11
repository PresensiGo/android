package com.rizalanggoro.presensigo.domain

data class Student(
    val id: Int = 0,
    val nis: String = "",
    val name: String = "",
    val classroomId: Int = 0
)

//fun DtoStudent.toDomain() = Student(
//    id = this.id,
//    nis = this.nis,
//    name = this.name,
//    classroomId = this.classroomId
//)