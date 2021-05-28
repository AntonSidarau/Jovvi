package com.jovvi.mobile.feature_question.presentation.models

import com.jovvi.mobile.business_category.model.QuestionModel

sealed class QuestionResult {

    data class QuestionsLoaded(val questions: List<QuestionModel>) : QuestionResult()
}