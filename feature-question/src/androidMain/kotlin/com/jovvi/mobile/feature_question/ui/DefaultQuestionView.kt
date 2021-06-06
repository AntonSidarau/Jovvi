package com.jovvi.mobile.feature_question.ui

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.google.android.material.appbar.MaterialToolbar
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_ui.adapter.DiffDelegatedAdapter
import com.jovvi.mobile.feature_question.R
import com.jovvi.mobile.feature_question.presentation.models.QuestionIntent
import com.jovvi.mobile.feature_question.presentation.models.QuestionIntent.Exit
import com.jovvi.mobile.feature_question.presentation.models.QuestionState
import com.jovvi.mobile.feature_question.ui.delegate.MarginItemDecoration
import com.jovvi.mobile.feature_question.ui.delegate.QuestionDelegate
import com.jovvi.mobile.feature_question.ui.delegate.QuestionDiffCallback
import com.jovvi.mobile.feature_question.ui.delegate.QuestionPageTransformer
import com.jovvi.mobile.feature_question.view.QuestionView
import dev.chrisbanes.insetter.applyInsetter

class DefaultQuestionView(
    private val root: View,
    topicModel: TopicModel
) : BaseMviView<QuestionState, QuestionIntent>(), QuestionView {

    private val adapter = DiffDelegatedAdapter(
        QuestionDiffCallback(),
        QuestionDelegate { dispatch(QuestionIntent.UpdateFavouriteStatus(it)) })

    // TODO diff util
    override val renderer: ViewRenderer<QuestionState> = object : ViewRenderer<QuestionState> {
        override fun render(model: QuestionState) {
            adapter.items = model.questions
        }
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

        toolbar.applyInsetter { type(statusBars = true) { margin() } }

        toolbar.title = topicModel.name

        viewPager.applyInsetter { type(navigationBars = true) { padding() } }
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
