package com.jovvi.mobile.common_ui.view_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.jovvi.mobile.common_ui.ext.getColorInt

open class InflateViewHolder(
    parent: ViewGroup,
    @LayoutRes itemLayoutResId: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(itemLayoutResId, parent, false)
) {

    protected fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String {
        return itemView.context.getString(stringRes, *formatArgs)
    }

    protected fun getString(@StringRes stringRes: Int): String {
        return itemView.context.getString(stringRes)
    }

    protected fun getColor(@ColorRes colorRes: Int): Int {
        return itemView.getColorInt(colorRes)
    }

    protected fun getDimens(@DimenRes dimenRes: Int): Float {
        return itemView.resources.getDimension(dimenRes)
    }
}
