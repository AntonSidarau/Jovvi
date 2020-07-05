package com.jovvi.mobile.common.ext

import kotlin.LazyThreadSafetyMode.NONE

fun <T> justLazy(block: () -> T): Lazy<T> {
    return lazy(NONE, block)
}
