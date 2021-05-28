package com.jovvi.mobile.common_db.dao

import com.jovvi.mobile.commondb.Topic

interface TopicDao {

    fun getTopicsByCategoryId(categoryId: Long): List<Topic>

    fun getTopicById(id: Long): Topic?
}