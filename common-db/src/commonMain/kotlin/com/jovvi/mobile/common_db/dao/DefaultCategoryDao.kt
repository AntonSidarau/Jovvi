package com.jovvi.mobile.common_db.dao

import com.jovvi.mobile.commondb.Category
import com.jovvi.mobile.commondb.CategoryQueries

class DefaultCategoryDao(private val queries: CategoryQueries) : CategoryDao {

    override fun getCategories(): List<Category> {
        return queries.getAllCategories().executeAsList()
    }

    override fun getCategoryById(id: Long): Category? {
        return queries.getCategoryById(id).executeAsOneOrNull()
    }
}