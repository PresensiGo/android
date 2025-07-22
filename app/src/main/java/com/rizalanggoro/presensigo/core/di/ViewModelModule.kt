package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.presentation.auth.AuthViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
}