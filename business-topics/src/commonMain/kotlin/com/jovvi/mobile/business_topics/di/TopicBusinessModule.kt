package com.jovvi.mobile.business_topics.di

import com.jovvi.mobile.business_topics.repository.TopicRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val topicBusinessModule = module {
    factory { TopicRepository(Dispatchers.Default, get(), get()) }
}
