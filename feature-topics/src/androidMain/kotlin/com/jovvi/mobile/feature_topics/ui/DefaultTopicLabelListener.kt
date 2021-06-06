package com.jovvi.mobile.feature_topics.ui

import com.jovvi.mobile.common_navigation.Router
import com.jovvi.mobile.feature_topics.navigation.TopicsNavigationProvider
import com.jovvi.mobile.feature_topics.presentation.TopicLabelListener
import com.jovvi.mobile.feature_topics.presentation.models.TopicIntent
import com.jovvi.mobile.feature_topics.presentation.models.TopicIntent.Exit

class DefaultTopicLabelListener(
    private val router: Router,
    private val navigationProvider: TopicsNavigationProvider
) : TopicLabelListener {

    override fun render(model: TopicIntent) {
        when (model) {
            is Exit -> router.exit()
            is TopicIntent.OpenQuestion -> router.forwardTo(
                navigationProvider.questionScreen(model.topic)
            )
        }
    }
}
