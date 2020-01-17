package com.elimak.krikey.ui.searchresult

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.Observer
import com.elimak.krikey.R

import com.elimak.krikey.databinding.FragmentSearchResultBinding
import com.elimak.krikey.ui.base.FragmentBase
import kotlinx.android.synthetic.main.fragment_search_result.*

class SearchResult : FragmentBase() {
    private lateinit var viewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchResultViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSearchResultBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewmodel = viewModel
            it.executePendingBindings()
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Get the catId passed through the Navigation component
        val catId = arguments?.let { SearchResultArgs.fromBundle(arguments!!).catId }
        if (catId != null) {
            viewModel.catId.set(catId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // After loading the data, we animate nicely the list items
        viewModel.loading.observe(this, Observer { loading ->
            if(!loading && !viewModel.listItems.isEmpty()) {
                val resId: Int = R.anim.layout_animation_fall_down
                val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, resId)
                searchCards.layoutAnimation = animation
            }
        })
    }
}
