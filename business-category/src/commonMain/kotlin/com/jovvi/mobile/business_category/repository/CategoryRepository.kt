package com.jovvi.mobile.business_category.repository

import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.common_db.dao.CategoryDao
import com.jovvi.mobile.common_mpp.Color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val dao: CategoryDao) {

    suspend fun getCategories(): List<CategoryModel> {
        return withContext(Dispatchers.Default) {
            dao.getCategories().map {
                CategoryModel(
                    it.id, Color(it.startColor), Color(it.endColor), it.name, it.description
                )
            }
        }
    }
}