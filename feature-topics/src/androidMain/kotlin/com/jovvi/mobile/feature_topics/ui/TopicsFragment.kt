package com.jovvi.mobile.feature_topics.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.common_di.Scopes
import com.jovvi.mobile.common_di.closeOnDestroy
import com.jovvi.mobile.common_di.createCustomScope
import com.jovvi.mobile.common_ui.ext.getSerializableAs
import com.jovvi.mobile.common_ui.ext.lazyMainThread
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.feature_topics.R
import com.jovvi.mobile.feature_topics.di.TopicScopes
import com.jovvi.mobile.feature_topics.presentation.TopicController
import com.jovvi.mobile.feature_topics.view.TopicView
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

class TopicsFragment : BaseFragment(R.layout.fragment_topics), AndroidScopeComponent {

    companion object {

        private const val ARG_CATEGORY = "arg_category"

        fun newInstance(category: CategoryModel): TopicsFragment {
            return TopicsFragment().apply {
                arguments = bundleOf(ARG_CATEGORY to category)
            }
        }
    }

    private val controller: TopicController by inject()
    private lateinit var viewProxy: TopicView

    override val scope: Scope by lazyMainThread {
        createCustomScope(TopicScopes.FEATURE).also {
            it.linkTo(it.getScope(Scopes.ACTIVITY))
            closeOnDestroy(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.setUp(extractCategoryModel())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewProxy = DefaultTopicView(view, extractCategoryModel())
        controller.onViewCreated(viewProxy, viewLifecycleOwner.lifecycle.asMviLifecycle())
    }

    override fun onBackPressed() {
        viewProxy.onExit()
    }

    override fun onDestroyView() {
        viewProxy.onDestroyView()
        super.onDestroyView()
    }

    private fun extractCategoryModel(): CategoryModel {
        return requireArguments().getSerializableAs(ARG_CATEGORY)
    }
}
