package com.example.filmsapp

import android.app.Application
import com.example.filmsapp.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    KoinModules.repositoryModule,
                    KoinModules.dataSourceModule,
                    KoinModules.viewModulesModule,
                    KoinModules.remoteModule
                )
            )
        }
    }
}