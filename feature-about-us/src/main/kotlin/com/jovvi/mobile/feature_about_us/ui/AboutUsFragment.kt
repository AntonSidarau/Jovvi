package com.jovvi.mobile.feature_about_us.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.jovvi.mobile.common_navigation.exit
import com.jovvi.mobile.common_ui.ext.addSystemBottomMargins
import com.jovvi.mobile.common_ui.ext.addSystemTopMargins
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.common_ui.fragment.Injector
import com.jovvi.mobile.feature_about_us.R

class AboutUsFragment : BaseFragment(R.layout.fragment_about_us) {

    companion object {

        fun newInstance(): Fragment {
            return AboutUsFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViewActions(view)
    }

    private fun bindViewActions(view: View) {
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        val btnShareTwitter: Button = view.findViewById(R.id.btn_share_twitter)

        toolbar.addSystemTopMargins()
        btnShareTwitter.addSystemBottomMargins()

        toolbar.setNavigationOnClickListener {
            Injector.navigator.exit()
        }
    }
}
