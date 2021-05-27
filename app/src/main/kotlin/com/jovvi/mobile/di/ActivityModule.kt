package com.jovvi.mobile.di

import com.jovvi.mobile.common_di.Scopes
import com.jovvi.mobile.root.ActivityHolder
import org.koin.core.qualifier.named
import org.koin.dsl.module

val activityModule = module {
    scope(named(Scopes.ACTIVITY)) {
        scoped { ActivityHolder() }
    }
}