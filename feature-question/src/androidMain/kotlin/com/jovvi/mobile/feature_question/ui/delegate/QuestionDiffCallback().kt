package com.jovvi.mobile.feature_question.ui.delegate

import androidx.recyclerview.widget.DiffUtil
import com.jovvi.mobile.business_category.model.QuestionModel

class QuestionDiffCallback : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is QuestionModel && newItem is QuestionModel) {
            oldItem.id == newItem.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is QuestionModel && newItem is QuestionModel) {
            oldItem.isFavourite == newItem.isFavourite
        } else {
            false
        }
    }

    override fun getChangePayload(oldItem: Any, newItem: Any): Any? {
        return QuestionPayload.IS_FAVOURITE
    }
}
