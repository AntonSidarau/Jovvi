package com.jovvi.mobile.common_db.dao

import com.jovvi.mobile.commondb.Question
import com.jovvi.mobile.commondb.QuestionQueries

class DefaultQuestionDao(private val queries: QuestionQueries) : QuestionDao {

    override fun getQuestionsByTopicId(topicId: Long): List<Question> {
        return queries.getAllQuestionsByTopicId(topicId).executeAsList()
    }
}