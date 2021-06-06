package com.jovvi.mobile.feature_about_us.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.jovvi.mobile.common_di.Scopes
import com.jovvi.mobile.common_navigation.Router
import com.jovvi.mobile.common_ui.ext.lazyMainThread
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.feature_about_us.R
import dev.chrisbanes.insetter.applyInsetter
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

class AboutUsFragment : BaseFragment(R.layout.fragment_about_us), AndroidScopeComponent {

    companion object {

        fun newInstance(): Fragment {
            return AboutUsFragment()
        }
    }

    private val router: Router by inject()

    override val scope: Scope by lazyMainThread {
        getKoin().getScope(Scopes.ACTIVITY)
        /*createCustomScope(AboutUsScopes.FEATURE).also {
            it.linkTo(it.getScope(Scopes.ACTIVITY))
            closeOnDestroy(it)
        }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViewActions(view)
    }

    override fun onBackPressed() {
        router.exit()
    }

    private fun bindViewActions(view: View) {
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        val btnShareTwitter: Button = view.findViewById(R.id.btn_share_twitter)

        toolbar.applyInsetter { type(statusBars = true) { margin() } }
        btnShareTwitter.applyInsetter { type(navigationBars = true) { margin() } }

        toolbar.setNavigationOnClickListener { router.exit() }
    }
}
