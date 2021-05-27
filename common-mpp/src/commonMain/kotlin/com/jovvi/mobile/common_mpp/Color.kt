package com.jovvi.mobile.common_mpp

data class Color(
    val red: Int,
    val green: Int,
    val blue: Int,
    val alpha: Int
) {
    val rgba: Int = alpha or
            blue.shl(8) or
            green.shl(16) or
            red.shl(24)

    val argb: Int = blue or
            green.shl(8) or
            red.shl(16) or
            alpha.shl(24)

    constructor(colorRGBA: Long) : this(
        red = (colorRGBA.shr(24) and 0xFF).toInt(),
        green = (colorRGBA.shr(16) and 0xFF).toInt(),
        blue = (colorRGBA.shr(8) and 0xFF).toInt(),
        alpha = (colorRGBA.shr(0) and 0xFF).toInt()
    )

    constructor(
        red: Int,
        green: Int,
        blue: Int
    ) : this(red, green, blue, 0xFF)

    constructor(rgbWithHashtag: String) : this(
        colorRGBA = rgbWithHashtag.substring(1).plus("ff").toLong(16)
    )
}
