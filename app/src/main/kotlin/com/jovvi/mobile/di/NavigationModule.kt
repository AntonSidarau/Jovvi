package com.jovvi.mobile.di

import com.jovvi.mobile.R
import com.jovvi.mobile.common_di.Scopes
import com.jovvi.mobile.common_navigation.DefaultNavigator
import com.jovvi.mobile.common_navigation.Navigator
import com.jovvi.mobile.common_navigation.Router
import com.jovvi.mobile.feature_category.navigation.CategoryNavigationProvider
import com.jovvi.mobile.feature_topics.navigation.TopicsNavigationProvider
import com.jovvi.mobile.navigation.provider.DefaultCategoryNavigationProvider
import com.jovvi.mobile.navigation.provider.DefaultTopicsNavigationProvider
import com.jovvi.mobile.root.ActivityHolder
import org.koin.core.qualifier.named
import org.koin.dsl.module

val navigationModule = module {

    scope(named(Scopes.ACTIVITY)) {
        factory<Navigator> {
            val activityHolder: ActivityHolder = get()
            DefaultNavigator(activityHolder.activity, R.id.root_container)
        }

        scoped { Router() }
    }

    factory<CategoryNavigationProvider> { DefaultCategoryNavigationProvider(get()) }
    factory<TopicsNavigationProvider> { DefaultTopicsNavigationProvider() }
}