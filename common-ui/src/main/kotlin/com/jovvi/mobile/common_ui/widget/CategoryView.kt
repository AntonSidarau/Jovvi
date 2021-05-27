package com.jovvi.mobile.common_ui.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.RECTANGLE
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.jovvi.mobile.common_ui.R
import com.jovvi.mobile.common_ui.ext.dpToPx
import com.jovvi.mobile.common_ui.ext.getColorInt
import com.jovvi.mobile.common_ui.ext.getThemeColor

class CategoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.categoryViewStyle
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val ELEVATION = 4F
    }

    private var patternType = 0

    private val gradientDrawable: GradientDrawable

    init {
        var startColor: Int = context.getColorInt(R.color.malibu)
        var endColor: Int = context.getColorInt(R.color.anakiwa)
        var cornerRadius: Float = context.resources.getDimension(R.dimen.corner_large_size)

        context.withStyledAttributes(attrs, R.styleable.CategoryView) {
            startColor = getColor(R.styleable.CategoryView_backgroundStartColor, startColor)
            endColor = getColor(R.styleable.CategoryView_backgroundEndColor, endColor)
            patternType = getInt(R.styleable.CategoryView_pattern, patternType)
            cornerRadius = getDimension(R.styleable.CategoryView_cornerRadius, cornerRadius)
        }

        gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.BL_TR, intArrayOf(startColor, endColor)
        ).apply {
            shape = RECTANGLE
            this.cornerRadius = cornerRadius
        }

        val rippleColor = context.getThemeColor(android.R.attr.colorControlActivated)
        background = RippleDrawable(ColorStateList.valueOf(rippleColor), gradientDrawable, null)
        elevation = context.dpToPx(ELEVATION)
    }

    fun applyAppearance(startColor: Int, endColor: Int, pattern: Int) {
        patternType = pattern
        gradientDrawable.colors = intArrayOf(startColor, endColor)
    }

    override fun onDraw(canvas: Canvas) {
    }
}
