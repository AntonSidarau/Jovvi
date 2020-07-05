package com.jovvi.mobile.common_mpp

import platform.UIKit.UIColor

fun Color.toUIColor(): UIColor {
    val maxColorValue: Int = 0xFF
    return UIColor(
        red = red.toDouble() / maxColorValue,
        green = green.toDouble() / maxColorValue,
        blue = blue.toDouble() / maxColorValue,
        alpha = alpha.toDouble() / maxColorValue
    )
}
