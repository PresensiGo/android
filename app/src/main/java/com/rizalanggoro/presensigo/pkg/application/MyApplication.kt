package com.rizalanggoro.presensigo.pkg.application

import android.app.Application
import com.rizalanggoro.presensigo.pkg.di.repositoryModule
import com.rizalanggoro.presensigo.pkg.di.serviceModule
import com.rizalanggoro.presensigo.pkg.di.viewModelModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                serviceModule,
                repositoryModule,
                viewModelModule,
            )
        }
    }
}