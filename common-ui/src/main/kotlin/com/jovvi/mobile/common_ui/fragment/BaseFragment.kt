package com.jovvi.mobile.common_ui.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

open class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    open fun onBackPressed() {}
}
