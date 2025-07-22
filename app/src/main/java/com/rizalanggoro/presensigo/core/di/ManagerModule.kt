package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.data.managers.TokenManager
import org.koin.dsl.module

val managerModule = module {
    single { TokenManager() }
}