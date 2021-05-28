package com.jovvi.mobile.feature_topics.presentation

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.common_mpp.mvi.RetainedMviController
import com.jovvi.mobile.feature_topics.presentation.models.TopicState
import com.jovvi.mobile.feature_topics.view.TopicView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class TopicController(
    private val storeFactory: TopicStoreFactory,
    private val labelListener: TopicLabelListener
) : RetainedMviController {

    private lateinit var store: TopicStore
    private var initialState: TopicState? = null

    override fun saveState() {
        initialState = store.state
    }

    fun setUp(categoryModel: CategoryModel) {
        store = storeFactory(initialState, categoryModel)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun onViewCreated(topicView: TopicView, lifecycle: Lifecycle) {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            topicView.events bindTo store
        }
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states bindTo topicView
            store.labels bindTo labelListener
        }
    }
}