package com.jovvi.mobile.feature_topics.presentation.models

import com.jovvi.mobile.business_category.model.CategoryModel

sealed class TopicAction {

    data class Start(val model: CategoryModel) : TopicAction()
}