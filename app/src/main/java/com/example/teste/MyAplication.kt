package com.example.teste

import android.app.Application
import com.example.teste.modules.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyAplication : Application() {
    private val modules = listOf(
        contactsModule, registerCardModule, paymentModule, repositoryData, primingModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyAplication)
            modules(modules)
        }
    }
}