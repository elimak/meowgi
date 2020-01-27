package com.elimak.krikey.ui.searchresult

import android.app.Application
import androidx.lifecycle.MutableLiveData
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

    val data: MutableLiveData<ResultPic> = MutableLiveData()
    val favorite: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        App.injector.inject(this)
        data.value = dataPoint

        favorite.value = repository.isFavorite(dataPoint)
    }

    fun onFavorite() {
        if(data.value != null){
            viewModelScope.launch {
                repository.updateFavorite(data.value!!, !favorite.value!!)
                favorite.value = !favorite.value!!
            }
        }
    }
}