package com.jovvi.mobile.feature_topics.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.business_topics.repository.TopicRepository
import com.jovvi.mobile.feature_topics.presentation.models.TopicAction
import com.jovvi.mobile.feature_topics.presentation.models.TopicAction.Idle
import com.jovvi.mobile.feature_topics.presentation.models.TopicAction.Start
import com.jovvi.mobile.feature_topics.presentation.models.TopicIntent
import com.jovvi.mobile.feature_topics.presentation.models.TopicResult
import com.jovvi.mobile.feature_topics.presentation.models.TopicState

class TopicStoreFactory(
    private val storeFactory: StoreFactory,
    private val repository: TopicRepository
) : (TopicState?, CategoryModel) -> TopicStore {

    override fun invoke(initialState: TopicState?, model: CategoryModel): TopicStore {
        return DefaultTopicStore(storeFactory, repository, initialState, model)
    }

    private class DefaultTopicStore(
        private val storeFactory: StoreFactory,
        private val repository: TopicRepository,
        initialState: TopicState?,
        categoryModel: CategoryModel
    ) : TopicStore, Store<TopicIntent, TopicState, TopicIntent> by storeFactory.create(
        name = "TopicStore",
        initialState = initialState ?: TopicState(categoryModel.title),
        bootstrapper = SimpleBootstrapper(if (initialState == null) Start(categoryModel) else Idle),
        executorFactory = { TopicExecutor(repository) },
        reducer = TopicReducer()
    )

    private class TopicExecutor(
        private val repository: TopicRepository
    ) : SuspendExecutor<TopicIntent, TopicAction, TopicState, TopicResult, TopicIntent>() {

        override suspend fun executeAction(action: TopicAction, getState: () -> TopicState) {
            when (action) {
                is Start -> {
                    val topics = repository.getTopicsByCategoryId(action.model.id)
                    dispatch(TopicResult.TopicsLoaded(topics))
                }
                is Idle -> {
                    // do nothing
                }
            }
        }

        override suspend fun executeIntent(intent: TopicIntent, getState: () -> TopicState) {
            publish(intent)
        }
    }

    private class TopicReducer : Reducer<TopicState, TopicResult> {

        override fun TopicState.reduce(result: TopicResult): TopicState {
            return when (result) {
                is TopicResult.TopicsLoaded -> copy(topics = result.topics)
            }
        }
    }
}
