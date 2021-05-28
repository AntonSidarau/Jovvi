package com.jovvi.mobile.feature_question.presentation.models

import com.jovvi.mobile.business_category.model.QuestionModel

data class QuestionState(
    val topicTitle: String,
    val questions: List<QuestionModel> = emptyList()
)