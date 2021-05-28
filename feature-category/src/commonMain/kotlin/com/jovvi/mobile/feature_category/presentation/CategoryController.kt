package com.jovvi.mobile.feature_category.presentation

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.jovvi.mobile.common_mpp.mvi.RetainedMviController
import com.jovvi.mobile.feature_category.presentation.models.CategoryState
import com.jovvi.mobile.feature_category.view.CategoryView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


class CategoryController(
    private val storeFactory: CategoryStoreFactory,
    private val labelListener: CategoryLabelListener
) : RetainedMviController {

    private lateinit var store: CategoryStore
    private var initialState: CategoryState? = null

    override fun saveState() {
        initialState = store.state
    }

    fun setUp() {
        store = storeFactory(initialState)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun onViewCreated(categoryView: CategoryView, lifecycle: Lifecycle) {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            categoryView.events bindTo store
        }
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states bindTo categoryView
            store.labels bindTo labelListener
        }
    }
}