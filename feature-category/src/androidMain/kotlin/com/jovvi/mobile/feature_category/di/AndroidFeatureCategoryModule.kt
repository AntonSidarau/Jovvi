package com.jovvi.mobile.feature_category.di

import com.jovvi.mobile.feature_category.presentation.CategoryLabelListener
import com.jovvi.mobile.feature_category.ui.DefaultCategoryLabelListener
import org.koin.core.qualifier.named
import org.koin.dsl.module

val androidCategoryFeatureModule = module {
    scope(named(CategoryScopes.FEATURE)) {
        factory<CategoryLabelListener> { DefaultCategoryLabelListener(get(), get()) }
    }
}