package com.jovvi.mobile.feature_category.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.jovvi.mobile.feature_category.presentation.models.CategoryIntent
import com.jovvi.mobile.feature_category.presentation.models.CategoryState

interface CategoryView : MviView<CategoryState, CategoryIntent> {

    fun onExit()

    fun onViewDestroyed()
}