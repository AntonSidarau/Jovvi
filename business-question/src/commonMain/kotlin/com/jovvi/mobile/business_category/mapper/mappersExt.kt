package com.jovvi.mobile.business_category.mapper

import com.jovvi.mobile.business_category.model.QuestionModel
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.commondb.Question

internal fun Question.toModel(topic: TopicModel): QuestionModel {
    val isFavourite = favourite ?: false
    return QuestionModel(id, name, isFavourite, topic.colorStart, topic.colorEnd)
}
