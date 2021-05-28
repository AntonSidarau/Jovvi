package com.jovvi.mobile.feature_question.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.jovvi.mobile.feature_question.presentation.models.QuestionIntent
import com.jovvi.mobile.feature_question.presentation.models.QuestionState

interface QuestionView : MviView<QuestionState, QuestionIntent> {

    fun onExit()

    fun onDestroyView()
}