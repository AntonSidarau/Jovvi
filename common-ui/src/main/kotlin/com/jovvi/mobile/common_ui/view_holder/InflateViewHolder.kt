package com.jovvi.mobile.common_ui.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.jovvi.mobile.common_ui.ext.getColorInt
import kotlinx.android.extensions.LayoutContainer

open class InflateViewHolder(
    parent: ViewGroup,
    @LayoutRes itemLayoutResId: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(itemLayoutResId, parent, false)
), LayoutContainer {

    override val containerView: View = itemView

    protected fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String {
        return itemView.context.getString(stringRes, *formatArgs)
    }

    protected fun getString(@StringRes stringRes: Int): String {
        return itemView.context.getString(stringRes)
    }

    protected fun getColor(@ColorRes colorRes: Int): Int {
        return containerView.getColorInt(colorRes)
    }
}
