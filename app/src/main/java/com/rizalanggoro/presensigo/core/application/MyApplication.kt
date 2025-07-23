package com.rizalanggoro.presensigo.core.application

import android.app.Application
import com.rizalanggoro.presensigo.core.di.managerModule
import com.rizalanggoro.presensigo.core.di.repositoryModule
import com.rizalanggoro.presensigo.core.di.serviceModule
import com.rizalanggoro.presensigo.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                managerModule,
                serviceModule,
                repositoryModule,
                viewModelModule,
            )
        }
    }
}