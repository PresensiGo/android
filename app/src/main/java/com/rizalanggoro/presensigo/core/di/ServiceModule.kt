package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.openapi.apis.AuthApi
import org.koin.dsl.module

val serviceModule = module {
    single { AuthApi() }
}