package com.elimak.krikey

import android.app.Application
import com.elimak.krikey.di.ApplicationComponent
import com.elimak.krikey.di.ApplicationModule
import com.elimak.krikey.di.DaggerApplicationComponent

class App: Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: App private set
    }
}
