package com.jovvi.mobile.feature_question.ui.delegate

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.jovvi.mobile.common_ui.ext.dpToPx
import com.jovvi.mobile.common_ui.view_holder.InflateViewHolder
import com.jovvi.mobile.feature_question.R

internal class QuestionDelegate() : AdapterDelegate<List<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent)
    }

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Question
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val vh = holder as ViewHolder
        val question = items[position] as Question

        vh.bind(question)
    }

    private class ViewHolder(
        parent: ViewGroup
    ) : InflateViewHolder(parent, R.layout.item_question) {

        private val tvQuestion: TextView = itemView.findViewById(R.id.tv_question)
        private val viewCard: ViewGroup = itemView.findViewById(R.id.view_card)
        private val ivFavourite: ImageView = itemView.findViewById(R.id.iv_favorite)

        private val gradientDrawable: GradientDrawable = GradientDrawable().apply {
            cornerRadius = getDimens(R.dimen.corner_large_size)
            shape = GradientDrawable.RECTANGLE
            orientation = GradientDrawable.Orientation.BL_TR
            color = ColorStateList.valueOf(getColor(R.color.white))
        }

        init {
            ivFavourite.setOnClickListener {
                Toast.makeText(it.context, "Favourite", Toast.LENGTH_SHORT).show()
            }

            viewCard.background = gradientDrawable
            viewCard.elevation = viewCard.dpToPx(6F)
        }

        fun bind(question: Question) {
            tvQuestion.text = question.name
            gradientDrawable.colors = intArrayOf(question.startColor, question.endColor)
        }
    }
}

data class Question(
    val name: String,
    val startColor: Int,
    val endColor: Int
)
