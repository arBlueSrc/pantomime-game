package com.example.pantomime

import android.app.Application
import com.example.pantomime.di.ApplicationModule
import com.example.pantomime.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PantomimApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PantomimApplication)
            modules(ApplicationModule, viewModelModules)
        }

    }
}