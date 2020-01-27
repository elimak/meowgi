package com.elimak.krikey.ui.searchresult

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.elimak.krikey.App
import com.elimak.krikey.BR
import com.elimak.krikey.R
import com.elimak.krikey.repository.ICatSearchRepository
import com.elimak.krikey.repository.IFavoriteRepository
import com.elimak.krikey.ui.base.ViewModelBase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.tatarka.bindingcollectionadapter2.OnItemBind
import javax.inject.Inject

@ExperimentalCoroutinesApi
open class ResultViewModel(application: Application) : ViewModelBase(application) {
    @Inject
    lateinit var catRepository: ICatSearchRepository
    @Inject
    lateinit var favRepository: IFavoriteRepository

    val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorVisible: MutableLiveData<Boolean> = MutableLiveData(false)

    // --------- Binding of the list items
    // Lib: me.tatarka.bindingcollectionadapter2

    val listItems: ObservableList<CardResultViewModel> = ObservableArrayList()
    val onItemBind: OnItemBind<CardResultViewModel> =
        OnItemBind { itemBinding, position, item ->
            itemBinding.set(
                BR.viewmodel,
                R.layout.card_search_result
            )
        }

    // --- End of list items bindings

    init {
        App.injector.inject(this)
    }
}
