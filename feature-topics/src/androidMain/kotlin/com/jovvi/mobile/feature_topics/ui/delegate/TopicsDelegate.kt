package com.jovvi.mobile.feature_topics.ui.delegate

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.Orientation
import android.graphics.drawable.GradientDrawable.RECTANGLE
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_ui.ext.dpToPx
import com.jovvi.mobile.common_ui.ext.getThemeColor
import com.jovvi.mobile.common_ui.view_holder.InflateViewHolder
import com.jovvi.mobile.feature_topics.R

class TopicsDelegate(
    private val onItemClicked: (TopicModel) -> Unit
) : AdapterDelegate<List<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent, onItemClicked)
    }

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is TopicModel
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val vh = holder as ViewHolder
        val category = items[position] as TopicModel

        vh.bind(category)
    }

    private class ViewHolder(
        parent: ViewGroup,
        private val onItemClicked: (TopicModel) -> Unit
    ) : InflateViewHolder(parent, R.layout.item_topics) {

        private val strokeDrawable: GradientDrawable
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_title)

        private lateinit var topic: TopicModel

        init {
            val container: FrameLayout = itemView.findViewById(R.id.topics_container)
            // TODO Change to shape
            val outerCornerRadius = getDimens(R.dimen.corner_large_size)
            val innerCornerRadius = outerCornerRadius - itemView.dpToPx(3F)
            val padding = itemView.dpToPx(4F).toInt()

            strokeDrawable = GradientDrawable(Orientation.BL_TR, intArrayOf()).apply {
                cornerRadius = outerCornerRadius
                shape = RECTANGLE
            }

            val fillDrawable = GradientDrawable().apply {
                cornerRadius = innerCornerRadius
                shape = RECTANGLE
                color = ColorStateList.valueOf(getColor(R.color.white))
            }

            container.background = RippleDrawable(
                ColorStateList.valueOf(itemView.getThemeColor(android.R.attr.colorControlActivated)),
                LayerDrawable(arrayOf(strokeDrawable, InsetDrawable(fillDrawable, padding))),
                null
            )

            itemView.setOnClickListener { onItemClicked(topic) }
        }

        fun bind(topic: TopicModel) {
            this.topic = topic

            strokeDrawable.colors = intArrayOf(topic.colorStart.argb, topic.colorEnd.argb)
            titleTextView.text = topic.name
        }
    }
}
