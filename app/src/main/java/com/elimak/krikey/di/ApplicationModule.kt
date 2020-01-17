package com.elimak.krikey.di

import android.app.Application
import android.content.Context
import com.elimak.krikey.App
import com.elimak.krikey.api.IApiServices
import com.elimak.krikey.repository.CatSearchRepository
import com.elimak.krikey.repository.FavoriteRepository
import com.elimak.krikey.repository.ICatSearchRepository
import com.elimak.krikey.repository.IFavoriteRepository

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: App) {

    @Provides
    @AppContext
    fun provideContext(): Context = baseApp.applicationContext

    @Provides
    @AppContext
    fun provideApplication(): Application = baseApp

    @Provides
    @Singleton
    protected fun provideCatSearchRepository(): ICatSearchRepository {
        return CatSearchRepository(baseApp)
    }

    @Provides
    @Singleton
    protected fun provideFavoriteRepository(): IFavoriteRepository {
        return FavoriteRepository(baseApp)
    }

    @Provides
    fun provideApiServices(): IApiServices {
        return IApiServices.create()
    }
}