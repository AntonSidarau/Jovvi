package com.jovvi.mobile.common_db.dao

import com.jovvi.mobile.commondb.Topic
import com.jovvi.mobile.commondb.TopicQueries

class DefaultTopicDao(private val queries: TopicQueries) : TopicDao {

    override fun getTopicsByCategoryId(categoryId: Long): List<Topic> {
        return queries.getTopicsByCategoryId(categoryId).executeAsList()
    }

    override fun getTopicById(id: Long): Topic? {
        return queries.getTopicById(id).executeAsOneOrNull()
    }
}