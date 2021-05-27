package com.jovvi.mobile.root

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jovvi.mobile.R
import com.jovvi.mobile.common_di.Scopes
import com.jovvi.mobile.common_di.closeOnDestroy
import com.jovvi.mobile.common_di.createCustomScope
import com.jovvi.mobile.common_navigation.Router
import com.jovvi.mobile.common_ui.ext.lazyMainThread
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.navigation.CategoryScreen
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

class RootActivity : AppCompatActivity(), AndroidScopeComponent {

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.root_container) as? BaseFragment

    private val router: Router by inject()

    override val scope: Scope by lazyMainThread {
        createCustomScope(Scopes.ACTIVITY).also { closeOnDestroy(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        get<ActivityHolder>().activity = this
        router.connectNavigator(get())

        setContentView(R.layout.activity_root)
        initUi()

        if (savedInstanceState == null) {
            router.newRootScreen(CategoryScreen)
        }
    }

    override fun onDestroy() {
        router.disconnectNavigator()
        super.onDestroy()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    private fun initUi() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}
