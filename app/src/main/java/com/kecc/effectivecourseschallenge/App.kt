package com.kecc.effectivecourseschallenge

import android.app.Application
import com.kecc.effectivecourseschallenge.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(
                mainModule
            ))
        }
    }
}