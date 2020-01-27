package com.elimak.krikey.ui.searchresult

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SearchResultViewModel(application: Application) : ResultViewModel(application) {

    val catId : MutableLiveData<Int> = MutableLiveData(0)
    val catName : MutableLiveData<String> = MutableLiveData("")

    fun render() {
        viewModelScope.launch {
            catRepository.getCategoryById(catId.value!!)
                .collect { catData ->
                catName.value = catData.name
            }
        }

        viewModelScope.launch {
            loading.value = true
            errorVisible.value = false
            try {
                val result = catRepository.getSearchByCatId(catId.value!!)

                for (data in result) {
                    listItems.add(CardResultViewModel(data, context))
                }

                loading.value = false
            } catch (failed: Exception) {
                Toast.makeText(context, failed.message, Toast.LENGTH_LONG).show()
                errorVisible.value = true
                loading.value = false
            }
        }
    }
}
