package com.jovvi.mobile.common_db.dao

import com.jovvi.mobile.commondb.Category

interface CategoryDao {

    fun getCategories(): List<Category>

    fun getCategoryById(id: Long): Category?
}