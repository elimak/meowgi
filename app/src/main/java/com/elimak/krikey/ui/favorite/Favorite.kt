package com.elimak.krikey.ui.favorite

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.Observer

import com.elimak.krikey.R
import com.elimak.krikey.databinding.FavoriteFragmentBinding
import com.elimak.krikey.ui.base.FragmentBase
import kotlinx.android.synthetic.main.favorite_fragment.*

class Favorite : FragmentBase() {

    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FavoriteFragmentBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewmodel = viewModel
            it.executePendingBindings()
        }.root
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