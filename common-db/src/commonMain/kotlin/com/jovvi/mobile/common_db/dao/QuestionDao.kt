package com.jovvi.mobile.common_db.dao

import com.jovvi.mobile.commondb.Question

interface QuestionDao {

    fun getQuestionsByTopicId(topicId: Long): List<Question>

    fun getQuestionById(questionId: Long): Question?

    fun updateQuestionFavouriteStatus(id: Long, isFavourite: Boolean)
}