package com.jovvi.mobile.feature_about_us.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.jovvi.mobile.common_navigation.Navigator
import com.jovvi.mobile.common_navigation.exit
import com.jovvi.mobile.common_ui.ext.addSystemBottomMargins
import com.jovvi.mobile.common_ui.ext.addSystemTopMargins
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.feature_about_us.R
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class AboutUsFragment : BaseFragment(R.layout.fragment_about_us), DIAware {

    companion object {

        fun newInstance(): Fragment {
            return AboutUsFragment()
        }
    }

    private val navigator: Navigator by instance()

    override val di: DI by closestDI()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViewActions(view)
    }

    override fun onBackPressed() {
        navigator.exit()
    }

    private fun bindViewActions(view: View) {
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        val btnShareTwitter: Button = view.findViewById(R.id.btn_share_twitter)

        toolbar.addSystemTopMargins()
        btnShareTwitter.addSystemBottomMargins()

        toolbar.setNavigationOnClickListener {
            navigator.exit()
        }
    }
}
