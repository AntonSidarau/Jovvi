package com.jovvi.mobile.feature_question.ui.delegate

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.jovvi.mobile.business_category.model.QuestionModel
import com.jovvi.mobile.common_ui.ext.dpToPx
import com.jovvi.mobile.common_ui.view_holder.InflateViewHolder
import com.jovvi.mobile.feature_question.R

internal class QuestionDelegate(
    private val onFavouriteClick: (QuestionModel) -> Unit
) : AdapterDelegate<List<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent, onFavouriteClick)
    }

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is QuestionModel
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val vh = holder as ViewHolder
        val question = items[position] as QuestionModel
        val hasPayload = payloads.isNotEmpty() && payloads[0] == QuestionPayload.IS_FAVOURITE

        if (hasPayload) {
            vh.bindIsFavourite(question)
        } else {
            vh.bind(question)
        }
    }

    private class ViewHolder(
        parent: ViewGroup,
        onFavouriteClick: (QuestionModel) -> Unit
    ) : InflateViewHolder(parent, R.layout.item_question) {

        private val tvQuestion: TextView = itemView.findViewById(R.id.tv_question)
        private val viewCard: ViewGroup = itemView.findViewById(R.id.view_card)
        private val ivFavourite: LottieAnimationView = itemView.findViewById(R.id.iv_favorite)

        private val gradientDrawable: GradientDrawable = GradientDrawable().apply {
            cornerRadius = getDimens(R.dimen.corner_large_size)
            shape = GradientDrawable.RECTANGLE
            orientation = GradientDrawable.Orientation.BL_TR
            color = ColorStateList.valueOf(getColor(R.color.white))
        }

        private lateinit var model: QuestionModel

        init {
            ivFavourite.addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                    ivFavourite.isEnabled = true
                    onFavouriteClick(model)
                }
            })

            ivFavourite.setOnClickListener {
                if (!model.isFavourite) {
                    ivFavourite.isEnabled = false
                    ivFavourite.playAnimation()

                } else {
                    onFavouriteClick(model)
                }
            }

            viewCard.background = gradientDrawable
            viewCard.elevation = viewCard.dpToPx(6F)
        }

        fun bind(question: QuestionModel) {
            this.model = question
            tvQuestion.text = question.text
            bindIsFavourite(question)
            gradientDrawable.colors = intArrayOf(question.colorStart.argb, question.colorEnd.argb)
        }

        fun bindIsFavourite(question: QuestionModel) {
            this.model = question
            ivFavourite.progress = if (question.isFavourite) 1F else 0F
            ivFavourite.isEnabled = true
        }
    }
}

internal enum class QuestionPayload {
    IS_FAVOURITE
}
