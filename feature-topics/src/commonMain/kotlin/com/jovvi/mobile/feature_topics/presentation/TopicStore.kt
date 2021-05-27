package com.jovvi.mobile.feature_topics.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.jovvi.mobile.feature_topics.presentation.models.TopicIntent
import com.jovvi.mobile.feature_topics.presentation.models.TopicState

interface TopicStore : Store<TopicIntent, TopicState, TopicIntent>