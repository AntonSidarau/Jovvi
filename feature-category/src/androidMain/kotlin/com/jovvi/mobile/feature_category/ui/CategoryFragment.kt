package com.jovvi.mobile.feature_category.ui

import android.os.Bundle
import android.view.View
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.jovvi.mobile.common_di.Scopes
import com.jovvi.mobile.common_di.closeOnDestroy
import com.jovvi.mobile.common_di.createCustomScope
import com.jovvi.mobile.common_mpp.mvi.saveStateOnDestroy
import com.jovvi.mobile.common_ui.ext.lazyMainThread
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.feature_category.R
import com.jovvi.mobile.feature_category.di.CategoryScopes
import com.jovvi.mobile.feature_category.presentation.CategoryController
import com.jovvi.mobile.feature_category.view.CategoryView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

class CategoryFragment : BaseFragment(R.layout.fragment_category), AndroidScopeComponent {

    private val controller: CategoryController by inject()

    private lateinit var viewProxy: CategoryView

    override val scope: Scope by lazyMainThread {
        createCustomScope(CategoryScopes.FEATURE).also {
            it.linkTo(it.getScope(Scopes.ACTIVITY))
            closeOnDestroy(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.apply {
            setUp()
            saveStateOnDestroy(this@CategoryFragment)
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewProxy = DefaultCategoryView(view)
        controller.onViewCreated(viewProxy, viewLifecycleOwner.lifecycle.asMviLifecycle())
    }

    override fun onDestroyView() {
        viewProxy.onViewDestroyed()
        super.onDestroyView()
    }

    override fun onBackPressed() {
        viewProxy.onExit()
    }
}
