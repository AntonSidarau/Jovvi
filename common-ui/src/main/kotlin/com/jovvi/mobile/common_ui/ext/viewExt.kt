package com.jovvi.mobile.common_ui.ext

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.use

fun View.getColorInt(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(context, colorRes)
}

@SuppressLint("Recycle")
fun View.getThemeColor(@AttrRes themeAttrRes: Int): Int {
    return context.obtainStyledAttributes(intArrayOf(themeAttrRes)).use {
        it.getColor(0, Color.MAGENTA)
    }
}

fun View.dpToPx(dp: Float): Float {
    return context.dpToPx(dp)
}

fun View.spToPx(sp: Float): Float {
    return context.spToPx(sp)
}
