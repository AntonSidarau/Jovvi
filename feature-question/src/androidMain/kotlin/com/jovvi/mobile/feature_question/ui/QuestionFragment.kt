package com.jovvi.mobile.feature_question.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_di.Scopes
import com.jovvi.mobile.common_di.closeOnDestroy
import com.jovvi.mobile.common_di.createCustomScope
import com.jovvi.mobile.common_mpp.mvi.saveStateOnDestroy
import com.jovvi.mobile.common_ui.ext.getSerializableAs
import com.jovvi.mobile.common_ui.ext.lazyMainThread
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.feature_question.R
import com.jovvi.mobile.feature_question.di.QuestionScopes
import com.jovvi.mobile.feature_question.presentation.QuestionController
import com.jovvi.mobile.feature_question.view.QuestionView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

class QuestionFragment : BaseFragment(R.layout.fragment_question), AndroidScopeComponent {

    companion object {

        private const val ARG_TOPIC = "arg_topic"

        fun newInstance(topic: TopicModel): Fragment {
            return QuestionFragment().apply {
                arguments = bundleOf(ARG_TOPIC to topic)
            }
        }
    }

    private val controller: QuestionController by inject()
    private lateinit var viewProxy: QuestionView

    override val scope: Scope by lazyMainThread {
        createCustomScope(QuestionScopes.FEATURE).also {
            it.linkTo(it.getScope(Scopes.ACTIVITY))
            closeOnDestroy(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.apply {
            setUp(extractTopic())
            saveStateOnDestroy(this@QuestionFragment)
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewProxy = DefaultQuestionView(view, extractTopic())
        controller.onViewCreated(viewProxy, viewLifecycleOwner.lifecycle.asMviLifecycle())
    }

    override fun onDestroyView() {
        viewProxy.onDestroyView()
        super.onDestroyView()
    }

    override fun onBackPressed() {
        viewProxy.onExit()
    }

    private fun extractTopic(): TopicModel {
        return requireArguments().getSerializableAs(ARG_TOPIC)
    }
}
