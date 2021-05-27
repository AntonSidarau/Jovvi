package com.jovvi.mobile.feature_category.presentation.models

import com.jovvi.mobile.business_category.model.CategoryModel

sealed class CategoryResult {

    data class CategoriesLoaded(val categories: List<CategoryModel>) : CategoryResult()
}