package com.jovvi.mobile.business_category.repository

import com.jovvi.mobile.business_category.mapper.toModel
import com.jovvi.mobile.business_category.model.QuestionModel
import com.jovvi.mobile.business_topics.model.TopicModel
import com.jovvi.mobile.common_db.dao.FavouriteQuestionDao
import com.jovvi.mobile.common_db.dao.QuestionDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FavouriteQuestionRepository(
    private val dispatcher: CoroutineDispatcher,
    private val favouriteQuestionDao: FavouriteQuestionDao,
    private val questionDao: QuestionDao
) {

    suspend fun addToFavourites(questionId: Long) {
        return withContext(dispatcher) {
            favouriteQuestionDao.addFavouriteQuestion(questionId)
            questionDao.updateQuestionFavouriteStatus(questionId, isFavourite = true)
        }
    }

    suspend fun removeFromFavourites(questionId: Long) {
        return withContext(dispatcher) {
            favouriteQuestionDao.deleteFavouriteQuestion(questionId)
            questionDao.updateQuestionFavouriteStatus(questionId, isFavourite = false)
        }
    }

    suspend fun getQuestions(): List<QuestionModel> {
        return withContext(dispatcher) {
            favouriteQuestionDao.getQuestions().map {
                val topic = TopicModel.favouriteQuestions("")
                it.toModel(topic)
            }
        }
    }
}
