package com.rizalanggoro.presensigo.pkg.di

import com.rizalanggoro.presensigo.internal.presentation.auth.AuthViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
}