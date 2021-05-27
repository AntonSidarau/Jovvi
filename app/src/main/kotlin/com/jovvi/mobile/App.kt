package com.jovvi.mobile

import android.app.Application
import com.jovvi.mobile.business_category.di.categoryBusinessModule
import com.jovvi.mobile.business_topics.di.topicBusinessModule
import com.jovvi.mobile.common.activity.ActivityCallbacks
import com.jovvi.mobile.common_db.di.databaseModule
import com.jovvi.mobile.di.activityModule
import com.jovvi.mobile.di.appModule
import com.jovvi.mobile.di.navigationModule
import com.jovvi.mobile.feature_category.di.androidCategoryFeatureModule
import com.jovvi.mobile.feature_category.di.categoryFeatureModule
import com.jovvi.mobile.feature_topics.di.androidTopicFeatureModule
import com.jovvi.mobile.feature_topics.di.topicFeatureModule
import com.jovvi.mobile.navigation.*
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                activityModule,
                navigationModule,
                databaseModule,

                categoryBusinessModule,
                topicBusinessModule,

                categoryFeatureModule,
                androidCategoryFeatureModule,
                topicFeatureModule,
                androidTopicFeatureModule
            )
        }

        val activityCallbacks: ActivityCallbacks = get()
        registerActivityLifecycleCallbacks(activityCallbacks)
    }
}
