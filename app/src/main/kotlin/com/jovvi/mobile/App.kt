package com.jovvi.mobile

import android.app.Application
import com.jovvi.mobile.common.activity.ActivityCallbacks
import com.jovvi.mobile.di.activityModule
import com.jovvi.mobile.di.appModule
import com.jovvi.mobile.di.navigationModule
import com.jovvi.mobile.navigation.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXContextTranslators
import org.kodein.di.instance

class App : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXContextTranslators)
        import(appModule(this@App))
        import(activityModule())
        import(navigationModule())
    }

    override fun onCreate() {
        super.onCreate()

        val activityCallbacks: ActivityCallbacks by di.instance()
        registerActivityLifecycleCallbacks(activityCallbacks)
    }
}
