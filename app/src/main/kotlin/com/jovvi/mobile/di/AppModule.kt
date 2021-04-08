package com.jovvi.mobile.di

import android.app.Application
import com.jovvi.mobile.common.activity.ActivityCallbacks
import com.jovvi.mobile.common.activity.CompositeActivityCallbacksListener
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun appModule(app: Application) = DI.Module("AppModule") {
    bind { instance(app) }

    bind<ActivityCallbacks> { singleton { CompositeActivityCallbacksListener() } }
}