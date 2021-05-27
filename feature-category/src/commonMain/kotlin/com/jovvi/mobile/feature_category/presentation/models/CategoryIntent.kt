package com.jovvi.mobile.feature_category.presentation.models

import com.jovvi.mobile.business_category.model.CategoryModel

sealed class CategoryIntent {

    data class OpenTopics(val category: CategoryModel) : CategoryIntent()
    object OpenHotQuestion : CategoryIntent()
    object OpenFavouriteQuestion : CategoryIntent()
    object OpenMore : CategoryIntent()
    object Exit : CategoryIntent()
}