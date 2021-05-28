package com.jovvi.mobile.feature_question.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.jovvi.mobile.feature_question.presentation.QuestionController
import com.jovvi.mobile.feature_question.presentation.QuestionStoreFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val questionFeatureModule = module {
    scope(named(QuestionScopes.FEATURE)) {
        scoped {
            QuestionController(
                QuestionStoreFactory(DefaultStoreFactory, get()),
                get()
            )
        }
    }
}