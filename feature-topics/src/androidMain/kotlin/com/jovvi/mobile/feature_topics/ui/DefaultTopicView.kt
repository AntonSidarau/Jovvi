package com.jovvi.mobile.feature_topics.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_ui.adapter.DefaultDelegatedAdapter
import com.jovvi.mobile.common_ui.ext.addSystemBottomPadding
import com.jovvi.mobile.common_ui.ext.addSystemTopPadding
import com.jovvi.mobile.common_ui.widget.CategoryView
import com.jovvi.mobile.feature_topics.R
import com.jovvi.mobile.feature_topics.presentation.models.TopicIntent
import com.jovvi.mobile.feature_topics.presentation.models.TopicIntent.Exit
import com.jovvi.mobile.feature_topics.presentation.models.TopicIntent.OpenQuestion
import com.jovvi.mobile.feature_topics.presentation.models.TopicState
import com.jovvi.mobile.feature_topics.ui.delegate.TopicsDelegate
import com.jovvi.mobile.feature_topics.view.TopicView

class DefaultTopicView(
    private val root: View,
    category: CategoryModel
) : BaseMviView<TopicState, TopicIntent>(), TopicView {

    private val adapter = DefaultDelegatedAdapter(TopicsDelegate(::onTopicClicked))

    override val renderer: ViewRenderer<TopicState> =
        diff {
            diff(get = TopicState::topics, compare = { a, b -> a === b }, set = adapter::setItems)
        }

    init {
        initUi(root, category)
        bindViewActions(root)
    }

    override fun onExit() {
        dispatch(Exit)
    }

    override fun onDestroyView() {
        root.findViewById<RecyclerView>(R.id.rv_topics)?.adapter = null
    }

    private fun initUi(view: View, category: CategoryModel) {
        val recyclerTopics: RecyclerView = view.findViewById(R.id.rv_topics)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvSubtitle: TextView = view.findViewById(R.id.tv_subtitle)
        val categoryView: CategoryView = view.findViewById(R.id.view_category)

        recyclerTopics.addSystemBottomPadding()
        categoryView.addSystemTopPadding()

        recyclerTopics.adapter = adapter
        recyclerTopics.layoutManager = GridLayoutManager(view.context, 2)

        categoryView.applyAppearance(category.colorStart.argb, category.colorEnd.argb, 0)
        tvTitle.text = category.title
        tvSubtitle.text = category.description
    }

    private fun bindViewActions(view: View) {
        val ivBack: ImageView = view.findViewById(R.id.iv_back)
        ivBack.setOnClickListener { dispatch(Exit) }
    }

    private fun onTopicClicked(topic: TopicModel) {
        dispatch(OpenQuestion(topic))
    }
}