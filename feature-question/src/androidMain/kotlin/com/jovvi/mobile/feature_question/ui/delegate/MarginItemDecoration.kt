package com.jovvi.mobile.feature_question.ui.delegate

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jovvi.mobile.common_ui.ext.dpToPx

internal class MarginItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val margin = context.dpToPx(36F).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = margin
        outRect.left = margin
    }
}
