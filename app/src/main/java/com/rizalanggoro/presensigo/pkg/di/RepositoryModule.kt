package com.rizalanggoro.presensigo.pkg.di

import com.rizalanggoro.presensigo.internal.data.repositories.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthRepository)
}