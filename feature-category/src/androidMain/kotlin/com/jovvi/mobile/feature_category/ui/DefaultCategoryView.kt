package com.jovvi.mobile.feature_category.ui

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.common_ui.adapter.DefaultDelegatedAdapter
import com.jovvi.mobile.feature_category.R
import com.jovvi.mobile.feature_category.presentation.models.CategoryIntent
import com.jovvi.mobile.feature_category.presentation.models.CategoryIntent.*
import com.jovvi.mobile.feature_category.presentation.models.CategoryState
import com.jovvi.mobile.feature_category.ui.delegate.CategoryDelegate
import com.jovvi.mobile.feature_category.view.CategoryView
import dev.chrisbanes.insetter.applyInsetter

internal class DefaultCategoryView(
    private val root: View
) : BaseMviView<CategoryState, CategoryIntent>(), CategoryView {

    private val adapter = DefaultDelegatedAdapter(CategoryDelegate(::onCategoryClicked))

    override val renderer: ViewRenderer<CategoryState> =
        diff {
            diff(get = CategoryState::list, compare = { a, b -> a === b }, set = adapter::setItems)
        }

    init {
        initUi(root)
        bindViewActions(root)
    }

    override fun onExit() {
        dispatch(Exit)
    }

    override fun onViewDestroyed() {
        root.findViewById<RecyclerView>(R.id.rv_category).adapter = null
    }

    private fun initUi(view: View) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val recyclerCategory: RecyclerView = view.findViewById(R.id.rv_category)

        tvTitle.applyInsetter { type(statusBars = true) { margin() } }
        recyclerCategory.applyInsetter { type(navigationBars = true) { padding() } }
        recyclerCategory.adapter = adapter
    }

    private fun bindViewActions(view: View) {
        val btnHotQuestion: Button = view.findViewById(R.id.btn_hot)
        val btnFavoriteQuestion: Button = view.findViewById(R.id.btn_favorite)
        val ivMore: ImageView = view.findViewById(R.id.iv_more)

        btnHotQuestion.setOnClickListener { dispatch(OpenHotQuestion) }
        btnFavoriteQuestion.setOnClickListener { dispatch(OpenFavouriteQuestion) }
        ivMore.setOnClickListener { dispatch(OpenMore) }
    }

    private fun onCategoryClicked(category: CategoryModel) {
        dispatch(OpenTopics(category))
    }
}
