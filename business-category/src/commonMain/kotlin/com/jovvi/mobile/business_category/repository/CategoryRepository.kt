package com.jovvi.mobile.business_category.repository

import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.common_db.dao.CategoryDao
import com.jovvi.mobile.common_mpp.Color
import com.jovvi.mobile.commondb.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CategoryRepository(
    private val dispatcher: CoroutineDispatcher,
    private val dao: CategoryDao
) {

    suspend fun getCategories(): List<CategoryModel> {
        return withContext(dispatcher) {
            dao.getCategories().map { it.toModel() }
        }
    }

    suspend fun getCategoryById(categoryId: Long): CategoryModel? {
        return withContext(dispatcher) {
            dao.getCategoryById(categoryId)?.toModel()
        }
    }

    private fun Category.toModel(): CategoryModel {
        return CategoryModel(id, Color(startColor), Color(endColor), name, description)
    }
}
