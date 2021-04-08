package com.jovvi.mobile.feature_category.navigation

import com.jovvi.mobile.business_category.model.Category
import com.jovvi.mobile.common_navigation.Screen

interface CategoryNavigationProvider {

    fun topicsScreen(category: Category): Screen

    fun hotQuestionScreen(): Screen

    fun favoriteQuestionsScreen(): Screen

    fun aboutUsScreen(): Screen
}
