package com.jovvi.mobile.common_ui.ext

import android.os.Bundle

inline fun <reified T> Bundle.getSerializableAs(name: String): T {
    return getSerializable(name) as T
}
