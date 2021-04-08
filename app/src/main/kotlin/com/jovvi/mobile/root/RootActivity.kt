package com.jovvi.mobile.root

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jovvi.mobile.R
import com.jovvi.mobile.common_navigation.Navigator
import com.jovvi.mobile.common_navigation.newRootScreen
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.navigation.CategoryScreen
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class RootActivity : AppCompatActivity(), DIAware {

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.root_container) as? BaseFragment

    override val di by closestDI()

    private val navigator: Navigator by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        initUi()

        if (savedInstanceState == null) {
            navigator.newRootScreen(CategoryScreen)
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    private fun initUi() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}
