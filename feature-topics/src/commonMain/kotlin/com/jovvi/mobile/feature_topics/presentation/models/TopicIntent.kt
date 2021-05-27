package com.jovvi.mobile.feature_topics.presentation.models

import com.jovvi.mobile.business_topics.model.TopicModel

sealed class TopicIntent {

    object Exit : TopicIntent()

    data class OpenQuestion(val topic: TopicModel) : TopicIntent()
}