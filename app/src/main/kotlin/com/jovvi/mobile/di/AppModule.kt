package com.jovvi.mobile.di

import com.jovvi.mobile.common.activity.ActivityCallbacks
import com.jovvi.mobile.common.activity.CompositeActivityCallbacksListener
import com.jovvi.mobile.common_db.DatabaseDriverFactory
import org.koin.dsl.module

val appModule = module {
    single<ActivityCallbacks> { CompositeActivityCallbacksListener() }
    single { DatabaseDriverFactory(get()).createDriver() }
}