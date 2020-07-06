package com.jovvi.mobile

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.jovvi.mobile.business_category.model.Category
import com.jovvi.mobile.business_topics.model.Topic
import com.jovvi.mobile.common.ext.justLazy
import com.jovvi.mobile.common_navigation.DefaultNavigator
import com.jovvi.mobile.common_navigation.Navigator
import com.jovvi.mobile.common_navigation.Screen
import com.jovvi.mobile.common_ui.fragment.Injector
import com.jovvi.mobile.common_ui.fragment.NavigationProvider
import com.jovvi.mobile.common_ui.fragment.TempDi
import com.jovvi.mobile.feature_category.navigation.CategoryNavigationProvider
import com.jovvi.mobile.feature_topics.navigation.TopicsNavigationProvider
import com.jovvi.mobile.navigation.QuestionScreen
import com.jovvi.mobile.navigation.TopicsScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.setUp(object : TempDi {

            private lateinit var innerNavigator: Navigator

            override val navigator: Navigator get() = innerNavigator

            override fun initNavigator(activity: AppCompatActivity): Navigator {
                innerNavigator = DefaultNavigator(activity, R.id.root_container)
                return innerNavigator
            }

            override val categoryNavigationProvider: NavigationProvider by justLazy {
                object : CategoryNavigationProvider {
                    override fun topicsScreen(category: Category): Screen = TopicsScreen(category)
                }
            }

            override val topicsNavigationProvider: NavigationProvider by justLazy {
                object : TopicsNavigationProvider {
                    override fun questionScreen(topic: Topic): Screen = QuestionScreen(topic)
                }
            }
        })
    }
}
