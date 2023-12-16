package com.personal.dadjokes

import android.app.Application
import com.personal.dadjokes.di.AppModule
import com.personal.dadjokes.di.AppModuleImpl

class DadJokesApplication: Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}