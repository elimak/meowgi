package com.elimak.krikey.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elimak.krikey.R
import com.elimak.krikey.db.vo.ResultPic

@BindingAdapter("imageData")
fun setImageUrl(imageView: ImageView, data: ResultPic?) {
    if(data?.url != null && data.url!!.isNotEmpty()) {
        val w = imageView.resources.displayMetrics.widthPixels
        var h = w
        var ratio = 1.00
        if (data.height != null && data.height!! > 0
            && data.width != null && data.width!! > 0) {
            ratio = data.height!! * 1.00 / data.width!!

            h = (w * ratio).toInt()
        }

        imageView.layout(0, 0, 0, 0)

        Glide.with(imageView.getContext())
            .load(data.url!!)
            .override(w,h)
            .error(R.drawable.ic_broken_image)
            .transition(DrawableTransitionOptions.withCrossFade(600))
            .into(imageView)
    }
}

@BindingAdapter("catSrc")
fun setCatSrc(imageView: ImageView, category: String?) {
    if(category !=null && category.isNotEmpty()) {
        when(category) {
            "hats" -> {
                imageView.setImageResource(R.drawable.ic_hat)
                imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.cat_color_0), android.graphics.PorterDuff.Mode.SRC_IN)
            }
            "space" -> {
                imageView.setImageResource(R.drawable.ic_planet)
                imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.cat_color_1), android.graphics.PorterDuff.Mode.SRC_IN)
            }
            "sunglasses" -> {
                imageView.setImageResource(R.drawable.ic_sunglasses)
                imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.cat_color_2), android.graphics.PorterDuff.Mode.SRC_IN)
            }
            "boxes" -> {
                imageView.setImageResource(R.drawable.ic_box)
                imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.cat_color_3), android.graphics.PorterDuff.Mode.SRC_IN)
            }
            "sinks" -> {
                imageView.setImageResource(R.drawable.ic_sink)
                imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.cat_color_4), android.graphics.PorterDuff.Mode.SRC_IN)

            }
            "ties" -> {
                imageView.setImageResource(R.drawable.ic_tie)
                imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.cat_color_5), android.graphics.PorterDuff.Mode.SRC_IN)
            }
            "clothes" -> {
                imageView.setImageResource(R.drawable.ic_clothes)
                imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.cat_color_6), android.graphics.PorterDuff.Mode.SRC_IN)
            }
        }
    }
}