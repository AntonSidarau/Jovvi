package com.jovvi.mobile.common_db.dao

import com.jovvi.mobile.commondb.FavouriteQuestionQueries
import com.jovvi.mobile.commondb.Question

class DefaultFavouriteQuestionDao(
    private val queries: FavouriteQuestionQueries
) : FavouriteQuestionDao {

    override fun getQuestions(): List<Question> {
        return queries.getAllFavouriteQuestions().executeAsList()
    }

    override fun addFavouriteQuestion(questionId: Long) {
        return queries.addFavouriteQuestion(questionId)
    }

    override fun deleteFavouriteQuestion(questionId: Long) {
        return queries.deleteFavouriteQuestionById(questionId)
    }
}