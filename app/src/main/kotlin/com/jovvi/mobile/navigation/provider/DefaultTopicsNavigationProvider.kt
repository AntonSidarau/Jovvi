package com.jovvi.mobile.navigation.provider

import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_navigation.Screen
import com.jovvi.mobile.feature_topics.navigation.TopicsNavigationProvider
import com.jovvi.mobile.navigation.QuestionScreen

class DefaultTopicsNavigationProvider : TopicsNavigationProvider {

    override fun questionScreen(topic: TopicModel): Screen = QuestionScreen(topic)
}