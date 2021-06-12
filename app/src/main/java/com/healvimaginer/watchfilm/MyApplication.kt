package com.healvimaginer.watchfilm

import android.app.Application
import com.healvimaginer.watchfilm.core.domain.di.*
import com.healvimaginer.watchfilm.di.filmCaseModule
import com.healvimaginer.watchfilm.di.filmModelModule
import com.healvimaginer.watchfilm.di.tvCaseModule
import com.healvimaginer.watchfilm.di.tvModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    tvCaseModule,
                    tvModelModule,
                    databaseModuleTv,
                    databaseModuleFavTv,
                    repositoryModuleTv,
                   filmCaseModule,
                    filmModelModule,
                    databaseModuleFilm,
                    databaseModuleFavFilm,
                    repositoryModuleFilm,
                    networkModule,
                )
            )
        }
    }
}