package com.jovvi.mobile.common_ui.fragment

import androidx.appcompat.app.AppCompatActivity
import com.jovvi.mobile.common_navigation.Navigator

//TODO remove
interface TempDi {

    val navigator: Navigator

    fun initNavigator(activity: AppCompatActivity): Navigator

    val categoryNavigationProvider: NavigationProvider

    val topicsNavigationProvider: NavigationProvider
}

object Injector {

    private lateinit var tempDi: TempDi

    fun setUp(tempDi: TempDi) {
        this.tempDi = tempDi
    }

    fun initNavigator(activity: AppCompatActivity): Navigator {
        return tempDi.initNavigator(activity)
    }

    val navigator: Navigator get() = tempDi.navigator

    val categoryNavigationProvider: NavigationProvider get() = tempDi.categoryNavigationProvider

    val topicsNavigationProvider: NavigationProvider get() = tempDi.topicsNavigationProvider
}

interface NavigationProvider
