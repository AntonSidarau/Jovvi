package com.jovvi.mobile.common_db.dao

import com.jovvi.mobile.commondb.Question
import com.jovvi.mobile.commondb.QuestionQueries

class DefaultQuestionDao(private val queries: QuestionQueries) : QuestionDao {

    override fun getQuestionsByTopicId(topicId: Long): List<Question> {
        TODO("Not yet implemented")
    }
}