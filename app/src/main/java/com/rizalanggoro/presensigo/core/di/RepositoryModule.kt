package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.data.repositories.AuthRepository
import com.rizalanggoro.presensigo.data.repositories.BatchRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthRepository)
    singleOf(::BatchRepository)
}