package com.jovvi.mobile.root

import androidx.appcompat.app.AppCompatActivity

class ActivityHolder {

    private var internalActivity: AppCompatActivity? = null

    var activity: AppCompatActivity
        get() {
            requireNotNull(internalActivity) { "ActivityHolder not initialized" }
            return internalActivity!!
        }
        set(value) {
            internalActivity = value
        }
}