package com.jovvi.mobile.common.activity

import android.app.Activity
import android.app.Application
import android.os.Bundle

interface ActivityCallbacks : Application.ActivityLifecycleCallbacks

open class ActivityCallbacksAdapter : ActivityCallbacks {

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) = Unit

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit
}

class CompositeActivityCallbacksListener : ActivityCallbacksAdapter() {
    private val listeners = mutableListOf<ActivityCallbacks>()

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        listeners.forEach { it.onActivityCreated(activity, bundle) }
    }

    override fun onActivityStarted(activity: Activity) {
        listeners.forEach { it.onActivityStarted(activity) }
    }

    override fun onActivityResumed(activity: Activity) {
        listeners.forEach { it.onActivityResumed(activity) }
    }

    override fun onActivityPaused(activity: Activity) {
        listeners.forEach { it.onActivityPaused(activity) }
    }

    override fun onActivityStopped(activity: Activity) {
        listeners.forEach { it.onActivityStopped(activity) }
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        listeners.forEach { it.onActivitySaveInstanceState(activity, bundle) }
    }

    override fun onActivityDestroyed(activity: Activity) {
        listeners.forEach { it.onActivityDestroyed(activity) }
    }

    fun addCallbacksListener(listener: ActivityCallbacks) {
        listeners.add(listener)
    }

    fun removeCallbacksListener(listener: ActivityCallbacks) {
        listeners.remove(listener)
    }
}
