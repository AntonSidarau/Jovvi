package com.jovvi.mobile.common.activity

import android.app.Activity
import android.content.Intent

data class ActivityResult(
    val requestCode: Int,
    val resultCode: Int,
    val data: Intent?
)

abstract class ActivityResultListener : ActivityCallbacksAdapter() {
    abstract val requestCode: Int

    override fun onActivityResultReceived(receiver: Activity, result: ActivityResult) {
        if (result.requestCode == requestCode) {
            when (result.resultCode) {
                Activity.RESULT_OK -> handlePositiveResult(receiver, result)
                else -> handleNegativeResult(receiver, result)
            }
        }
    }

    abstract fun handlePositiveResult(receiver: Activity, result: ActivityResult)

    abstract fun handleNegativeResult(receiver: Activity, result: ActivityResult)
}
