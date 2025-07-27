package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.data.repositories.AttendanceRepository
import com.rizalanggoro.presensigo.data.repositories.AuthRepository
import com.rizalanggoro.presensigo.data.repositories.BatchRepository
import com.rizalanggoro.presensigo.data.repositories.ClassroomRepository
import com.rizalanggoro.presensigo.data.repositories.MajorRepository
import com.rizalanggoro.presensigo.data.repositories.ResetRepository
import com.rizalanggoro.presensigo.data.repositories.StudentRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthRepository)
    singleOf(::AttendanceRepository)
    singleOf(::BatchRepository)
    singleOf(::ClassroomRepository)
    singleOf(::MajorRepository)
    singleOf(::ResetRepository)
    singleOf(::StudentRepository)
}