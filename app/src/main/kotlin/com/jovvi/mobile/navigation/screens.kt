package com.jovvi.mobile.navigation

import androidx.fragment.app.Fragment
import com.jovvi.mobile.business_category.model.Category
import com.jovvi.mobile.common_navigation.Screen
import com.jovvi.mobile.feature_category.ui.CategoryFragment
import com.jovvi.mobile.feature_topics.ui.TopicsFragment

object CategoryScreen : Screen() {

    override val fragment: Fragment get() = CategoryFragment()
}

class TopicsScreen(private val category: Category) : Screen() {

    override val fragment: Fragment get() = TopicsFragment.newInstance(category)
}
