package com.jovvi.mobile.feature_category.ui

import com.jovvi.mobile.common_navigation.Router
import com.jovvi.mobile.feature_category.navigation.CategoryNavigationProvider
import com.jovvi.mobile.feature_category.presentation.CategoryLabelListener
import com.jovvi.mobile.feature_category.presentation.models.CategoryIntent
import com.jovvi.mobile.feature_category.presentation.models.CategoryIntent.*

class DefaultCategoryLabelListener(
    private val router: Router,
    private val navigationProvider: CategoryNavigationProvider
) : CategoryLabelListener {

    override fun render(label: CategoryIntent) {
        when (label) {
            is OpenHotQuestion -> router.forwardTo(navigationProvider.hotQuestionScreen())
            is OpenFavouriteQuestion -> router.forwardTo(navigationProvider.favoriteQuestionsScreen())
            is OpenMore -> router.forwardTo(navigationProvider.aboutUsScreen())
            is OpenTopics -> router.forwardTo(navigationProvider.topicsScreen(label.category))
            is Exit -> router.exit()
        }
    }
}