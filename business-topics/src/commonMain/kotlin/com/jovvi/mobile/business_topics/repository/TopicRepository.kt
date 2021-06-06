package com.jovvi.mobile.business_topics.repository

import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.business_category.repository.CategoryRepository
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_db.dao.TopicDao
import com.jovvi.mobile.commondb.Topic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class TopicRepository(
    private val dispatcher: CoroutineDispatcher,
    private val topicDao: TopicDao,
    private val repository: CategoryRepository
) {

    suspend fun getTopicsByCategoryId(categoryId: Long): List<TopicModel> {
        return withContext(dispatcher) {
            val categoryJob = async { repository.getCategoryById(categoryId) }
            val topicsJob = async { topicDao.getTopicsByCategoryId(categoryId) }

            val topics = topicsJob.await()
            val categoryModel = categoryJob.await() ?: return@withContext emptyList()

            topics.map { it.toModel(categoryModel) }
        }
    }

    suspend fun getTopicById(id: Long): TopicModel? {
        return withContext(dispatcher) {
            val topic = topicDao.getTopicById(id)

            if (topic != null) {
                repository
                    .getCategoryById(topic.categoryId)
                    ?.let { topic.toModel(it) }
            } else {
                null
            }
        }
    }

    private fun Topic.toModel(categoryModel: CategoryModel): TopicModel {
        return TopicModel(id, name, categoryModel.colorStart, categoryModel.colorEnd)
    }
}
