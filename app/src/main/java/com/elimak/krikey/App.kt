package com.elimak.krikey

import android.app.Application
import com.elimak.krikey.di.ApplicationComponent
import com.elimak.krikey.di.ApplicationModule
import com.elimak.krikey.di.DaggerApplicationComponent

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    fun setup() {
        injector = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        injector.inject(this)
    }

    companion object {
        lateinit var instance: App private set
        lateinit var injector: ApplicationComponent private set
    }
}
