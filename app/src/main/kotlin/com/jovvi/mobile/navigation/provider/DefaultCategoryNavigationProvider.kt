package com.jovvi.mobile.navigation.provider

import android.app.Application
import com.jovvi.mobile.R
import com.jovvi.mobile.business_category.model.Category
import com.jovvi.mobile.business_topics.model.Topic
import com.jovvi.mobile.common_mpp.Color
import com.jovvi.mobile.common_navigation.Screen
import com.jovvi.mobile.feature_category.navigation.CategoryNavigationProvider
import com.jovvi.mobile.navigation.AboutUsScreen
import com.jovvi.mobile.navigation.FavoriteQuestionScreen
import com.jovvi.mobile.navigation.HotQuestionScreen
import com.jovvi.mobile.navigation.TopicsScreen

class DefaultCategoryNavigationProvider(private val app: Application) : CategoryNavigationProvider {

    override fun topicsScreen(category: Category): Screen = TopicsScreen(category)

    override fun hotQuestionScreen(): Screen = HotQuestionScreen

    override fun favoriteQuestionsScreen(): Screen {
        val topic = Topic(
            app.getString(R.string.question_favorite_title),
            colorStart = Color(red = 0xbb, green = 0xa1, blue = 0xff),
            colorEnd = Color(red = 0xcf, green = 0xbd, blue = 0xff)
        )
        return FavoriteQuestionScreen(topic)
    }

    override fun aboutUsScreen(): Screen = AboutUsScreen
}