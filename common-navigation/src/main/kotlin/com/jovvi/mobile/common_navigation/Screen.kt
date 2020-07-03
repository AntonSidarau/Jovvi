package com.jovvi.mobile.common_navigation

import androidx.fragment.app.Fragment

abstract class Screen {

    open val screenKey: String = javaClass.canonicalName.orEmpty()

    abstract val fragment: Fragment
}
