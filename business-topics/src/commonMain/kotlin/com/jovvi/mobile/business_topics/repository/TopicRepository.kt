package com.jovvi.mobile.business_topics.repository

import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_db.dao.CategoryDao
import com.jovvi.mobile.common_db.dao.TopicDao
import com.jovvi.mobile.common_mpp.Color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class TopicRepository(
    private val topicDao: TopicDao,
    private val categoryDao: CategoryDao
) {

    suspend fun getTopicsByCategoryId(categoryId: Long): List<TopicModel> {
        return withContext(Dispatchers.Default) {
            val categoryJob = async { categoryDao.getCategoryById(categoryId) }
            val topicsJob = async { topicDao.getTopicsByCategoryId(categoryId) }

            val category = categoryJob.await()
            val topics = topicsJob.await()

            if (category == null) {
                return@withContext emptyList()
            }

            topics.map {
                TopicModel(
                    it.name,
                    Color(category.startColor),
                    Color(category.endColor)
                )
            }
        }
    }
}