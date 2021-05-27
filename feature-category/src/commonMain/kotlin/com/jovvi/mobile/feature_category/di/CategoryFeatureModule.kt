package com.jovvi.mobile.feature_category.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.jovvi.mobile.feature_category.presentation.CategoryController
import com.jovvi.mobile.feature_category.presentation.CategoryStoreFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val categoryFeatureModule = module {
    scope(named(CategoryScopes.FEATURE)) {
        scoped {
            CategoryController(
                CategoryStoreFactory(DefaultStoreFactory, get()),
                get()
            )
        }
    }
}