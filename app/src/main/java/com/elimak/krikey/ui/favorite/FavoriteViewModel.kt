package com.elimak.krikey.ui.favorite

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.elimak.krikey.R
import com.elimak.krikey.db.vo.ResultPic
import com.elimak.krikey.repository.FavoriteRepository
import com.elimak.krikey.ui.searchresult.CardResultViewModel
import com.elimak.krikey.ui.searchresult.ResultViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

@UseExperimental(ExperimentalCoroutinesApi::class)
class FavoriteViewModel (application: Application) : ResultViewModel(application) {
    val titleFavCount: MutableLiveData<String> = MutableLiveData("")

    init {
        render()
        viewModelScope.launch {
            favRepository.broadcast.consumeEach {
                when(it) {
                    is FavoriteRepository.DeleteFavorite -> removeFav(it.data)
                }
            }
        }
    }

    private fun render() {
        viewModelScope.launch {
            loading.value = true
            errorVisible.value = false
            try {
                val result = favRepository.getFavorites()

                for (data in result) {
                    listItems.add(CardResultViewModel(data, context))
                }

                updateTitle()

                loading.value = false
            } catch (failed: Exception) {
                Toast.makeText(context, failed.message, Toast.LENGTH_LONG).show()
                errorVisible.value = true
                loading.value = false
            }
        }
    }

    private fun removeFav(resultPic: ResultPic) {
        var foundIndex = -1
        for(i in 0 until listItems.size) {
            if(listItems.get(i).data.value!!.id == resultPic.id) {
                foundIndex = i
            }
        }

        if(foundIndex > -1){
            listItems.removeAt(foundIndex)
        }

        updateTitle()
    }

    private fun updateTitle() {
        if(listItems.size != 1) {
            titleFavCount.value = context.getString(R.string.favorites_count, listItems.size)
        } else {
            titleFavCount.value = context.getString(R.string.favorite_one)
        }
    }
}
