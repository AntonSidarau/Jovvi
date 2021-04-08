package com.jovvi.mobile.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.jovvi.mobile.R
import com.jovvi.mobile.common_navigation.DefaultNavigator
import com.jovvi.mobile.common_navigation.Navigator
import com.jovvi.mobile.feature_category.navigation.CategoryNavigationProvider
import com.jovvi.mobile.feature_topics.navigation.TopicsNavigationProvider
import com.jovvi.mobile.navigation.provider.DefaultCategoryNavigationProvider
import com.jovvi.mobile.navigation.provider.DefaultTopicsNavigationProvider
import org.kodein.di.*
import org.kodein.di.bindings.WeakContextScope

fun navigationModule() = DI.Module("NavigationModule") {

    bind<Navigator> {
        scoped(WeakContextScope.of<Activity>()).singleton {
            DefaultNavigator(context as AppCompatActivity, R.id.root_container)
        }
    }

    bind<CategoryNavigationProvider> {
        singleton { DefaultCategoryNavigationProvider(instance()) }
    }
    bind<TopicsNavigationProvider> { singleton { DefaultTopicsNavigationProvider() } }
}