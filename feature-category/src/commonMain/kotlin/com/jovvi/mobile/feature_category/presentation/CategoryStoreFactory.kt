package com.jovvi.mobile.feature_category.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.jovvi.mobile.business_category.repository.CategoryRepository
import com.jovvi.mobile.feature_category.presentation.models.CategoryAction
import com.jovvi.mobile.feature_category.presentation.models.CategoryAction.Idle
import com.jovvi.mobile.feature_category.presentation.models.CategoryAction.Start
import com.jovvi.mobile.feature_category.presentation.models.CategoryIntent
import com.jovvi.mobile.feature_category.presentation.models.CategoryResult
import com.jovvi.mobile.feature_category.presentation.models.CategoryState

class CategoryStoreFactory(
    private val storeFactory: StoreFactory,
    private val repository: CategoryRepository
) : (CategoryState?) -> CategoryStore {

    override fun invoke(initialState: CategoryState?): CategoryStore {
        return DefaultCategoryStore(storeFactory, repository, initialState)
    }

    private class DefaultCategoryStore(
        private val storeFactory: StoreFactory,
        private val repository: CategoryRepository,
        initialState: CategoryState?
    ) : CategoryStore, Store<CategoryIntent, CategoryState, CategoryIntent> by storeFactory.create(
        name = "CategoryStore",
        initialState = initialState ?: CategoryState(),
        bootstrapper = SimpleBootstrapper(if (initialState == null) Start else Idle),
        executorFactory = { CategoryExecutor(repository) },
        reducer = CategoryReducer()
    )

    private class CategoryExecutor(
        private val repository: CategoryRepository
    ) : SuspendExecutor<CategoryIntent, CategoryAction, CategoryState, CategoryResult, CategoryIntent>() {

        override suspend fun executeAction(action: CategoryAction, getState: () -> CategoryState) {
            when (action) {
                is Start -> {
                    val categories = repository.getCategories()
                    dispatch(CategoryResult.CategoriesLoaded(categories))
                }
                is Idle -> {
                    // do nothing
                }
            }
        }

        override suspend fun executeIntent(intent: CategoryIntent, getState: () -> CategoryState) {
            publish(intent)
        }
    }

    private class CategoryReducer : Reducer<CategoryState, CategoryResult> {

        override fun CategoryState.reduce(result: CategoryResult): CategoryState {
            return when (result) {
                is CategoryResult.CategoriesLoaded -> copy(list = result.categories)
            }
        }
    }
}