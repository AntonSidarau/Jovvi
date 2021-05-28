package com.jovvi.mobile.feature_category.presentation.models

sealed class CategoryAction {

    object Start : CategoryAction()

    object Idle : CategoryAction()
}