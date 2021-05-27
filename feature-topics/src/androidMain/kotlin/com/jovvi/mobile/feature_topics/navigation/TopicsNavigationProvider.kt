package com.jovvi.mobile.feature_topics.navigation

import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_navigation.Screen

interface TopicsNavigationProvider {

    fun questionScreen(topic: TopicModel): Screen
}
