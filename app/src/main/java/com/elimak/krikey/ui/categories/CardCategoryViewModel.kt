package com.elimak.krikey.ui.categories

import android.view.View
import androidx.databinding.ObservableField
import androidx.navigation.Navigation
import com.elimak.krikey.App
import com.elimak.krikey.db.vo.Category
import com.elimak.krikey.repository.ICatSearchRepository
import com.elimak.krikey.ui.maintabs.MainTabsDirections
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CardCategoryViewModel(dataPoint: Category) {

    @Inject
    lateinit var repository: ICatSearchRepository

    val data: ObservableField<Category> = ObservableField()

    init {
        App.instance.getApplicationComponent().inject(this)
        data.set(dataPoint)
    }

    fun onClick(view: View) {
        if(data.get() != null) {
            val action = MainTabsDirections.actionToSearchResult()
            action.setCatId(data.get()!!.id)
            Navigation.findNavController(view).navigate(action)
        }
    }
}