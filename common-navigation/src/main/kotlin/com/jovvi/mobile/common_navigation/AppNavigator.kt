package com.jovvi.mobile.common_navigation

import androidx.appcompat.app.AppCompatActivity

class AppNavigator(
    private val activity: AppCompatActivity,
    containerId: Int
) : DefaultNavigator(activity.supportFragmentManager, containerId) {

    override fun activityBack() {
        activity.finish()
    }
}