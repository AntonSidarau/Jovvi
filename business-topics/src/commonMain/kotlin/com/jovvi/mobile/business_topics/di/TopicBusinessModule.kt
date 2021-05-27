package com.jovvi.mobile.business_topics.di

import com.jovvi.mobile.business_topics.repository.TopicRepository
import org.koin.dsl.module

val topicBusinessModule = module {
    factory { TopicRepository(get(), get()) }
}