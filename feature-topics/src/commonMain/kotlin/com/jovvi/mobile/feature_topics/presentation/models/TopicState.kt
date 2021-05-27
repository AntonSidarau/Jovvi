package com.jovvi.mobile.feature_topics.presentation.models

import com.jovvi.mobile.business_topics.model.TopicModel

data class TopicState(
    val title: String,
    val topics: List<TopicModel> = emptyList()
)