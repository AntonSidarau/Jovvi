package com.jovvi.mobile.feature_question.ui.delegate

import android.content.Context
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.jovvi.mobile.common_ui.ext.dpToPx
import kotlin.math.abs

private const val MIN_SCALE = 0.1F
private const val MIN_ALPHA = 0.4F

internal class QuestionPageTransformer(context: Context) : ViewPager2.PageTransformer {

    private val currentItemMargin = context.dpToPx(48F).toInt()
    private val nextItemMargin = context.dpToPx(24F).toInt()

    override fun transformPage(page: View, position: Float) {
        val pageTranslationX = currentItemMargin + nextItemMargin
        with(page) {
            translationX = -pageTranslationX * position
            scaleY = 1 - (MIN_SCALE * abs(position))
            scaleX = 1 - (MIN_SCALE * abs(position))
            alpha = MIN_ALPHA + (1 - abs(position))
        }
    }
}
