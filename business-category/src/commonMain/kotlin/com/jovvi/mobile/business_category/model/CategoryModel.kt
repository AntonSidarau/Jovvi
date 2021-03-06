package com.jovvi.mobile.business_category.model

import com.jovvi.mobile.common_mpp.Color
import com.jovvi.mobile.common_mpp.Serializable

data class CategoryModel(
    val id: Long,
    val colorStart: Color,
    val colorEnd: Color,
    val title: String,
    val description: String
) : Serializable
