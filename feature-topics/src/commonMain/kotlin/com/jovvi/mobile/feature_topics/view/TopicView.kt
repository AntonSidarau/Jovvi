package com.jovvi.mobile.feature_topics.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.jovvi.mobile.feature_topics.presentation.models.TopicIntent
import com.jovvi.mobile.feature_topics.presentation.models.TopicState

interface TopicView : MviView<TopicState, TopicIntent> {

    fun onExit()

    fun onDestroyView()
}