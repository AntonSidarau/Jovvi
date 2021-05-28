package com.jovvi.mobile.business_category.model

import com.jovvi.mobile.common_mpp.Color

data class QuestionModel(
    val id: Long,
    val text: String,
    val isFavourite: Boolean,
    val colorStart: Color,
    val colorEnd: Color
)