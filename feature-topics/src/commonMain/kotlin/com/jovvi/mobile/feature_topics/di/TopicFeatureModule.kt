package com.jovvi.mobile.feature_topics.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.jovvi.mobile.feature_topics.presentation.TopicController
import com.jovvi.mobile.feature_topics.presentation.TopicStoreFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val topicFeatureModule = module {
    scope(named(TopicScopes.FEATURE)) {
        scoped {
            TopicController(
                TopicStoreFactory(DefaultStoreFactory, get()),
                get()
            )
        }
    }
}