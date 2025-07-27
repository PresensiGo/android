package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.data.managers.TokenPayloadManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {
    singleOf(::TokenManager)
    singleOf(::TokenPayloadManager)
}