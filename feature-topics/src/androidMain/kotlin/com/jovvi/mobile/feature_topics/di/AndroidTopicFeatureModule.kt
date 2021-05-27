package com.jovvi.mobile.feature_topics.di

import com.jovvi.mobile.feature_topics.presentation.TopicLabelListener
import com.jovvi.mobile.feature_topics.ui.DefaultTopicLabelListener
import org.koin.core.qualifier.named
import org.koin.dsl.module

val androidTopicFeatureModule = module {
    scope(named(TopicScopes.FEATURE)) {
        factory<TopicLabelListener> { DefaultTopicLabelListener(get(), get()) }
    }
}