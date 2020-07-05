package com.jovvi.mobile.common.activity

class RequestPermissionResult(
    val requestCode: Int,
    val permissions: Array<out String>,
    val grantResults: IntArray
)
