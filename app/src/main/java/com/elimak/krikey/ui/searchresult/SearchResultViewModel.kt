package com.elimak.krikey.ui.searchresult

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.elimak.krikey.util.addOnPropertyChanged
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SearchResultViewModel(application: Application) : ResultViewModel(application) {

    val catId : ObservableInt = ObservableInt()
    val catName : ObservableField<String> = ObservableField("")

    init {
        catId.addOnPropertyChanged { render() }
        if(catId.get() > 0) {
            render()
        }
        render()
    }

    private fun render() {
        viewModelScope.launch {
            catRepository.getCategoryById(catId.get())
                .collect { catData ->
                catName.set(catData.name)
            }
        }

        viewModelScope.launch {
            loading.value = true
            errorVisible.value = false
            try {
                val result = catRepository.getSearchByCatId(catId.get())

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
