package com.jovvi.mobile.common_ui.ext

fun <T> lazyMainThread(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)