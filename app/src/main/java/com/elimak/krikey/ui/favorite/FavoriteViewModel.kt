package com.elimak.krikey.ui.favorite

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import com.elimak.krikey.R
import com.elimak.krikey.db.vo.ResultPic
import com.elimak.krikey.ui.searchresult.CardResultViewModel
import com.elimak.krikey.ui.searchresult.ResultViewModel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class FavoriteViewModel (application: Application) : ResultViewModel(application) {
    val titleFavCount: ObservableField<String> = ObservableField("")

    init {
        render()
        viewModelScope.launch {
            favRepository.updateDelete.consumeEach {
                removeFav(it)
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
            if(listItems.get(i).data.get()!!.id == resultPic.id) {
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
            titleFavCount.set(context.getString(R.string.favorites_count, listItems.size))
        } else {
            titleFavCount.set(context.getString(R.string.favorite_one))
        }
    }
}
