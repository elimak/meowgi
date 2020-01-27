package com.elimak.krikey.di

import android.content.Context
import com.elimak.krikey.App
import com.elimak.krikey.repository.CatSearchRepository
import com.elimak.krikey.ui.categories.CardCategoryViewModel
import com.elimak.krikey.ui.categories.ListViewModel
import com.elimak.krikey.ui.maintabs.MainTabsViewModel
import com.elimak.krikey.ui.searchresult.CardResultViewModel
import com.elimak.krikey.ui.searchresult.ResultViewModel

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: App)

    fun inject(viewModel: CardCategoryViewModel)
    fun inject(viewModel: ListViewModel)
    fun inject(viewModel: MainTabsViewModel)
    fun inject(viewModel: ResultViewModel)
    fun inject(viewModel: CardResultViewModel)

    fun inject(repository: CatSearchRepository)

    @AppContext
    fun getContext(): Context
}