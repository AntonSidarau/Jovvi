package com.jovvi.mobile.feature_topics.presentation.models

import com.jovvi.mobile.business_topics.model.TopicModel

sealed class TopicResult {

    data class TopicsLoaded(val topics: List<TopicModel>) : TopicResult()
}