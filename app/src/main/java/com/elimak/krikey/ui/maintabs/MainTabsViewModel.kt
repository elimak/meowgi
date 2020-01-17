package com.elimak.krikey.ui.maintabs

import android.app.Application
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.elimak.krikey.App
import com.elimak.krikey.R
import com.elimak.krikey.repository.IFavoriteRepository
import com.elimak.krikey.ui.base.ViewModelBase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainTabsViewModel(application: Application) : ViewModelBase(application) {
    @Inject
    lateinit var repository: IFavoriteRepository

    init {
        App.instance.getApplicationComponent().inject(this)
        viewModelScope.launch {
            repository.initiateFavorite()
        }
    }

    fun navigate(item: MenuItem, view: View) {
        var action: NavDirections? = null

        when (item.itemId) {
            R.id.menu_about -> {
                action= MainTabsDirections.actionToAboutFragment()
            }
        }

        action?.let { Navigation.findNavController(view).navigate(action) }
    }
}
