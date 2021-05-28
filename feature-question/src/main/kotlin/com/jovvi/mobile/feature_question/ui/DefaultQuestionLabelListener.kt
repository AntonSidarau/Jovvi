package com.jovvi.mobile.feature_question.ui

import com.jovvi.mobile.common_navigation.Router
import com.jovvi.mobile.feature_question.presentation.QuestionLabelListener
import com.jovvi.mobile.feature_question.presentation.models.QuestionLabel
import com.jovvi.mobile.feature_question.presentation.models.QuestionLabel.Exit

class DefaultQuestionLabelListener(
    private val router: Router
) : QuestionLabelListener {

    override fun render(label: QuestionLabel) {
        return when (label) {
            is Exit -> router.exit()
        }
    }
}