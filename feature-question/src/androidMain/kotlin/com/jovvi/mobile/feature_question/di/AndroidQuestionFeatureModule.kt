package com.jovvi.mobile.feature_question.di

import com.jovvi.mobile.feature_question.presentation.QuestionLabelListener
import com.jovvi.mobile.feature_question.ui.DefaultQuestionLabelListener
import org.koin.core.qualifier.named
import org.koin.dsl.module

val androidQuestionFeatureModule = module {
    scope(named(QuestionScopes.FEATURE)) {
        factory<QuestionLabelListener> { DefaultQuestionLabelListener(get()) }
    }
}