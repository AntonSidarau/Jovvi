package com.jovvi.mobile.feature_question.presentation

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_mpp.mvi.RetainedMviController
import com.jovvi.mobile.feature_question.presentation.models.QuestionState
import com.jovvi.mobile.feature_question.view.QuestionView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class QuestionController(
    private val storeFactory: QuestionStoreFactory,
    private val labelListener: QuestionLabelListener
) : RetainedMviController {

    private lateinit var store: QuestionStore
    private var initialState: QuestionState? = null

    override fun saveState() {
        initialState = store.state
    }

    fun setUp(topicModel: TopicModel) {
        store = storeFactory(initialState, topicModel)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun onViewCreated(questionView: QuestionView, lifecycle: Lifecycle) {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            questionView.events bindTo store
        }
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states bindTo questionView
            store.labels bindTo labelListener
        }
    }
}