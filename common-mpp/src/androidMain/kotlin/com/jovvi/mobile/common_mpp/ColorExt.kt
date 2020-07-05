package com.jovvi.mobile.common_mpp

import androidx.annotation.ColorInt

@ColorInt
fun Color.colorInt(): Int {
    return argb.toInt()
}
