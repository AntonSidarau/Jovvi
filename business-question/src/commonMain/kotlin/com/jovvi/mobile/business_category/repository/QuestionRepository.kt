package com.jovvi.mobile.business_category.repository

import com.jovvi.mobile.business_category.model.QuestionModel
import com.jovvi.mobile.business_topics.repository.TopicRepository
import com.jovvi.mobile.common_db.dao.QuestionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class QuestionRepository(
    private val questionDao: QuestionDao,
    private val repository: TopicRepository
) {

    // TODO move dispatchers to upper level
    suspend fun getQuestionsByTopicId(topicId: Long): List<QuestionModel> {
        return withContext(Dispatchers.Default) {
            val questionsJob = async { questionDao.getQuestionsByTopicId(topicId) }
            val topicsJob = async { repository.getTopicById(topicId) }

            val questions = questionsJob.await()
            val topic = topicsJob.await() ?: return@withContext emptyList()

            questions.map {
                val isFavourite = it.favourite ?: false
                QuestionModel(it.id, it.name, isFavourite, topic.colorStart, topic.colorEnd)
            }
        }
    }
}