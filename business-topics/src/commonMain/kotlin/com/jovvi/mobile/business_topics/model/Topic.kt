package com.jovvi.mobile.business_topics.model

import com.jovvi.mobile.common_mpp.Color
import com.jovvi.mobile.common_mpp.Serializable

data class Topic(
    val name: String,
    val colorStart: Color,
    val colorEnd: Color
) : Serializable
