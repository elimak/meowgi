package com.elimak.krikey.ui.searchresult

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.elimak.krikey.App
import com.elimak.krikey.db.vo.ResultPic
import com.elimak.krikey.repository.IFavoriteRepository
import com.elimak.krikey.ui.base.ViewModelBase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CardResultViewModel(dataPoint: ResultPic, application: Application) : ViewModelBase(application) {

    @Inject
    lateinit var repository: IFavoriteRepository

    val data: ObservableField<ResultPic> = ObservableField()
    val favorite: ObservableBoolean = ObservableBoolean(false)

    init {
        App.instance.getApplicationComponent().inject(this)
        data.set(dataPoint)

        favorite.set(repository.isFavorite(dataPoint))
    }

    fun onFavorite() {
        if(data.get() != null){
            viewModelScope.launch {
                repository.updateFavorite(data.get()!!, !favorite.get())
                favorite.set(!favorite.get())
            }
        }
    }
}