package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.presentation.auth.AuthViewModel
import com.rizalanggoro.presensigo.presentation.classroom.ClassroomViewModel
import com.rizalanggoro.presensigo.presentation.home.HomeViewModel
import com.rizalanggoro.presensigo.presentation.student.StudentViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ClassroomViewModel)
    viewModelOf(::StudentViewModel)
}