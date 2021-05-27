package com.jovvi.mobile.navigation.provider

import android.app.Application
import com.jovvi.mobile.R
import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_navigation.Screen
import com.jovvi.mobile.feature_category.navigation.CategoryNavigationProvider
import com.jovvi.mobile.navigation.AboutUsScreen
import com.jovvi.mobile.navigation.FavoriteQuestionScreen
import com.jovvi.mobile.navigation.HotQuestionScreen
import com.jovvi.mobile.navigation.TopicsScreen

class DefaultCategoryNavigationProvider(private val app: Application) : CategoryNavigationProvider {

    override fun topicsScreen(category: CategoryModel): Screen = TopicsScreen(category)

    override fun hotQuestionScreen(): Screen = HotQuestionScreen

    override fun favoriteQuestionsScreen(): Screen {
        val topic = TopicModel.favouriteQuestions(app.getString(R.string.question_favorite_title))
        return FavoriteQuestionScreen(topic)
    }

    override fun aboutUsScreen(): Screen = AboutUsScreen
}