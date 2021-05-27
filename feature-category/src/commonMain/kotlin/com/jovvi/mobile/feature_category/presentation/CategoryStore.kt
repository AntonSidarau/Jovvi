package com.jovvi.mobile.feature_category.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.jovvi.mobile.feature_category.presentation.models.CategoryIntent
import com.jovvi.mobile.feature_category.presentation.models.CategoryState

interface CategoryStore : Store<CategoryIntent, CategoryState, CategoryIntent>