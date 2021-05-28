package com.jovvi.mobile.feature_question.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.jovvi.mobile.business_category.repository.QuestionRepository
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.feature_question.presentation.models.*
import com.jovvi.mobile.feature_question.presentation.models.QuestionAction.Idle
import com.jovvi.mobile.feature_question.presentation.models.QuestionAction.Start
import com.jovvi.mobile.feature_question.presentation.models.QuestionResult.QuestionsLoaded

class QuestionStoreFactory(
    private val storeFactory: StoreFactory,
    private val repository: QuestionRepository
) : (QuestionState?, TopicModel) -> QuestionStore {

    override fun invoke(initialState: QuestionState?, model: TopicModel): QuestionStore {
        return DefaultQuestionStore(storeFactory, repository, initialState, model)
    }

    private class DefaultQuestionStore(
        private val storeFactory: StoreFactory,
        private val repository: QuestionRepository,
        initialState: QuestionState?,
        topicModel: TopicModel
    ) : QuestionStore, Store<QuestionIntent, QuestionState, QuestionLabel> by storeFactory.create(
        name = "QuestionStore",
        initialState = initialState ?: QuestionState(topicModel.name),
        bootstrapper = SimpleBootstrapper(if (initialState == null) Start(topicModel) else Idle),
        executorFactory = { QuestionExecutor(repository) },
        reducer = QuestionReducer()
    )

    private class QuestionExecutor(
        private val repository: QuestionRepository
    ) : SuspendExecutor<QuestionIntent, QuestionAction, QuestionState, QuestionResult, QuestionLabel>() {

        override suspend fun executeAction(action: QuestionAction, getState: () -> QuestionState) {
            when (action) {
                is Start -> {
                    val questions = repository.getQuestionsByTopicId(action.topicModel.id)
                    dispatch(QuestionsLoaded(questions))
                }
                is Idle -> {
                    // do nothing
                }
            }
        }

        override suspend fun executeIntent(intent: QuestionIntent, getState: () -> QuestionState) {
            when (intent) {
                QuestionIntent.Exit -> publish(QuestionLabel.Exit)
            }
        }
    }

    private class QuestionReducer : Reducer<QuestionState, QuestionResult> {

        override fun QuestionState.reduce(result: QuestionResult): QuestionState {
            return when (result) {
                is QuestionsLoaded -> copy(questions = result.questions)
            }
        }
    }
}