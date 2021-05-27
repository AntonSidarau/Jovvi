package com.jovvi.mobile.navigation

import androidx.fragment.app.Fragment
import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_navigation.Screen
import com.jovvi.mobile.feature_about_us.ui.AboutUsFragment
import com.jovvi.mobile.feature_category.ui.CategoryFragment
import com.jovvi.mobile.feature_hot_question.ui.HotQuestionFragment
import com.jovvi.mobile.feature_question.ui.QuestionFragment
import com.jovvi.mobile.feature_topics.ui.TopicsFragment

object CategoryScreen : Screen() {

    override val fragment: Fragment get() = CategoryFragment()
}

class TopicsScreen(private val category: CategoryModel) : Screen() {

    override val fragment: Fragment get() = TopicsFragment.newInstance(category)
}

class QuestionScreen(private val topic: TopicModel) : Screen() {

    override val fragment: Fragment get() = QuestionFragment.newInstance(topic)
}

object HotQuestionScreen : Screen() {

    override val fragment: Fragment get() = HotQuestionFragment.newInstance()
}

class FavoriteQuestionScreen(private val topic: TopicModel) : Screen() {

    override val fragment: Fragment get() = QuestionFragment.newInstance(topic)
}

object AboutUsScreen : Screen() {

    override val fragment: Fragment get() = AboutUsFragment.newInstance()
}
