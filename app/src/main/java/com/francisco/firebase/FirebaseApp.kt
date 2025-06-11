package com.francisco.firebase

import android.app.Application
import com.francisco.firebase.di.appModule
import com.francisco.firebase.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FirebaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FirebaseApp)
            modules(firebaseModule, appModule)
        }
    }
}