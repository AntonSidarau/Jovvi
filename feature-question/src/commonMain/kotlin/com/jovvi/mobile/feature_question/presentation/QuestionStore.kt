package com.jovvi.mobile.feature_question.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.jovvi.mobile.feature_question.presentation.models.QuestionIntent
import com.jovvi.mobile.feature_question.presentation.models.QuestionLabel
import com.jovvi.mobile.feature_question.presentation.models.QuestionState

interface QuestionStore : Store<QuestionIntent, QuestionState, QuestionLabel>
