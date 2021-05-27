package com.jovvi.mobile.common_di

import android.content.ComponentCallbacks
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeID

fun FragmentActivity.closeOnDestroy(scope: Scope) {
    lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                if (!isChangingConfigurations) {
                    scope.close()
                }
            }
        }
    })
}

fun Fragment.closeOnDestroy(scope: Scope) {
    lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                if (!requireActivity().isChangingConfigurations) {
                    scope.close()
                }
            }
        }
    })
}

fun ComponentCallbacks.createCustomScope(id: ScopeID): Scope {
    return getKoin().getOrCreateScope(id, named(id))
}