package com.elimak.krikey.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.elimak.krikey.R

object AnimationUtil{
    fun fadeIn(view: View, context: Context?, offset: Long = 0) {
        animate(view, context, offset, R.anim.fade_in)
    }

    fun slideIn(view: View, context: Context?, offset: Long = 0) {
        animate(view, context, offset, R.anim.slide_in)
    }

    private fun animate(view: View, context: Context?, offset: Long, id: Int) {
        if (view.visibility != View.VISIBLE) {
            view.visibility = View.VISIBLE
        }

        val anim = AnimationUtils.loadAnimation(context, id)

        view.startAnimation(anim)
        anim.setStartOffset(offset)
    }
}