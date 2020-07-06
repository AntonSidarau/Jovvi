package com.jovvi.mobile.feature_topics.navigation

import com.jovvi.mobile.business_topics.model.Topic
import com.jovvi.mobile.common_navigation.Screen
import com.jovvi.mobile.common_ui.fragment.NavigationProvider

interface TopicsNavigationProvider : NavigationProvider {

    fun questionScreen(topic: Topic): Screen
}
