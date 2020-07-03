package com.jovvi.mobile.common_ui.ext


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.use

fun Context.dpToPx(dp: Float): Float {
    return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun Context.spToPx(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)
}

fun Context.getColorInt(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

@SuppressLint("Recycle")
fun Context.getThemeColor(@AttrRes themeAttrRes: Int): Int {
    return obtainStyledAttributes(intArrayOf(themeAttrRes)).use {
        it.getColor(0, Color.MAGENTA)
    }
}
