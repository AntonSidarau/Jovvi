package com.jovvi.mobile.root

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jovvi.mobile.R
import com.jovvi.mobile.common.activity.ActivityCallbacks
import com.jovvi.mobile.common.activity.ActivityResult
import com.jovvi.mobile.common.activity.CompositeActivityCallbacksListener
import com.jovvi.mobile.common.activity.RequestPermissionResult
import com.jovvi.mobile.common_navigation.Navigator
import com.jovvi.mobile.common_navigation.newRootScreen
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.common_ui.fragment.Injector
import com.jovvi.mobile.navigation.CategoryScreen

class RootActivity : AppCompatActivity() {

    private val callbacksListener: ActivityCallbacks = CompositeActivityCallbacksListener()

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.root_container) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        callbacksListener.onActivityCreated(this, savedInstanceState)

        initUi()

        if (savedInstanceState == null) {
            val navigator: Navigator = Injector.initNavigator(this)
            navigator.newRootScreen(CategoryScreen)
        }
    }

    override fun onStart() {
        super.onStart()
        callbacksListener.onActivityStarted(this)
    }

    override fun onResume() {
        super.onResume()
        callbacksListener.onActivityResumed(this)
    }

    override fun onPause() {
        callbacksListener.onActivityPaused(this)
        super.onPause()
    }

    override fun onStop() {
        callbacksListener.onActivityStopped(this)
        super.onStop()
    }

    override fun onDestroy() {
        callbacksListener.onActivityDestroyed(this)
        super.onDestroy()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = ActivityResult(requestCode, resultCode, data)
        callbacksListener.onActivityResultReceived(this, result)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        callbacksListener.onRequestPermissionsResultReceived(
            this, RequestPermissionResult(requestCode, permissions, grantResults)
        )
    }

    private fun initUi() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}
