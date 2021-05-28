package com.jovvi.mobile.feature_question.ui

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.google.android.material.appbar.MaterialToolbar
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_ui.adapter.DefaultDelegatedAdapter
import com.jovvi.mobile.common_ui.ext.addSystemBottomPadding
import com.jovvi.mobile.common_ui.ext.addSystemTopMargins
import com.jovvi.mobile.feature_question.R
import com.jovvi.mobile.feature_question.presentation.models.QuestionIntent
import com.jovvi.mobile.feature_question.presentation.models.QuestionIntent.Exit
import com.jovvi.mobile.feature_question.presentation.models.QuestionState
import com.jovvi.mobile.feature_question.ui.delegate.MarginItemDecoration
import com.jovvi.mobile.feature_question.ui.delegate.QuestionDelegate
import com.jovvi.mobile.feature_question.ui.delegate.QuestionPageTransformer
import com.jovvi.mobile.feature_question.view.QuestionView

class DefaultQuestionView(
    private val root: View,
    topicModel: TopicModel
) : BaseMviView<QuestionState, QuestionIntent>(), QuestionView {

    private val adapter = DefaultDelegatedAdapter(QuestionDelegate())

    override val renderer: ViewRenderer<QuestionState> =
        diff {
            diff(
                get = QuestionState::questions,
                compare = { a, b -> a === b },
                set = adapter::setItems
            )
        }

    init {
        initUi(root, topicModel)
        bindViewActions(root)
    }

    override fun onExit() {
        dispatch(Exit)
    }

    override fun onDestroyView() {
        root.findViewById<ViewPager2>(R.id.view_pager).adapter = null
    }

    private fun initUi(view: View, topicModel: TopicModel) {
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        val viewPager: ViewPager2 = view.findViewById(R.id.view_pager)

        toolbar.addSystemTopMargins()

        toolbar.title = topicModel.name

        viewPager.addSystemBottomPadding()
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = adapter
        viewPager.addItemDecoration(MarginItemDecoration(view.context))
        viewPager.setPageTransformer(QuestionPageTransformer(view.context))
    }

    private fun bindViewActions(view: View) {
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { dispatch(Exit) }
    }
}