package com.jovvi.mobile.feature_category.presentation

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.jovvi.mobile.feature_category.view.CategoryView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


class CategoryController(
    private val storeFactory: CategoryStoreFactory,
    private val labelListener: CategoryLabelListener
) {

    private var innerStore: CategoryStore? = null

    fun setUp() {
        if (innerStore == null) {
            innerStore = storeFactory()
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun onViewCreated(categoryView: CategoryView, lifecycle: Lifecycle) {
        requireNotNull(innerStore) { "Call setUp before onViewCreated" }
        val store = innerStore!!

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            categoryView.events bindTo store
        }
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states bindTo categoryView
            store.labels bindTo labelListener
        }
    }
}