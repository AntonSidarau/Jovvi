package com.jovvi.mobile.feature_question.presentation.models

import com.jovvi.mobile.business_category.model.QuestionModel

sealed class QuestionIntent {

    object Exit : QuestionIntent()

    data class UpdateFavouriteStatus(val questionModel: QuestionModel) : QuestionIntent()
}
