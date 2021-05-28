package com.jovvi.mobile.feature_question.presentation.models

import com.jovvi.mobile.business_topics.model.TopicModel

sealed class QuestionAction {

    data class Start(val topicModel: TopicModel) : QuestionAction()

    object Idle : QuestionAction()
}