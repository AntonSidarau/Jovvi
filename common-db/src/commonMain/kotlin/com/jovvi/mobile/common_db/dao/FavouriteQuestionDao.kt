package com.jovvi.mobile.common_db.dao

import com.jovvi.mobile.commondb.Question

interface FavouriteQuestionDao {

    fun getQuestions(): List<Question>

    fun addFavouriteQuestion(questionId: Long)

    fun deleteFavouriteQuestion(questionId: Long)
}