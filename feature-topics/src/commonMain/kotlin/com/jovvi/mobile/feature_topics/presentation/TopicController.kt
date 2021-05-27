package com.jovvi.mobile.feature_topics.presentation

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.feature_topics.view.TopicView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class TopicController(
    private val storeFactory: TopicStoreFactory,
    private val labelListener: TopicLabelListener
) {

    private var innerStore: TopicStore? = null

    fun setUp(categoryModel: CategoryModel) {
        if (innerStore == null) {
            innerStore = storeFactory(categoryModel)
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun onViewCreated(topicView: TopicView, lifecycle: Lifecycle) {
        requireNotNull(innerStore) { "Call setUp before onViewCreated" }
        val store = innerStore!!

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            topicView.events bindTo store
        }
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states bindTo topicView
            store.labels bindTo labelListener
        }
    }
}