package com.elimak.krikey.ui.categories

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.elimak.krikey.App
import com.elimak.krikey.BR
import com.elimak.krikey.R
import com.elimak.krikey.db.vo.Category
import com.elimak.krikey.repository.ICatSearchRepository
import com.elimak.krikey.ui.base.ViewModelBase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.OnItemBind
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ListViewModel(application: Application) : ViewModelBase(application) {

    @Inject lateinit var repository: ICatSearchRepository

    // --------- Binding of the list items
    // Lib: me.tatarka.bindingcollectionadapter2

    val listItems: ObservableList<CardCategoryViewModel> = ObservableArrayList()
    val onItemBind: OnItemBind<CardCategoryViewModel> =
        OnItemBind { itemBinding, position, item ->
            itemBinding.set(
                BR.viewmodel,
                R.layout.card_category
            )
        }

    // --- End of list items bindings

    val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorVisible: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        App.injector.inject(this)
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            loading.value = true
            errorVisible.value = false

            try {
                // "force = false" means that we are loading the data from the local db if we already have some entries
                // but we could imagine pulling the data from online if the last refresh is older than "n" days/hours...
                val result: List<Category> = repository.fetchCategories(false)
                listItems.clear()
                for (data: Category in result) {
                    listItems.add(
                        CardCategoryViewModel(data)
                    )
                }

            } catch (failed: Exception) {
                if(listItems.isEmpty()) {
                    errorVisible.value = true
                }

                Toast.makeText(context, failed.message, Toast.LENGTH_LONG).show()
            }
            loading.value = false
        }
    }
}
