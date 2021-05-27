package com.jovvi.mobile.business_topics.model

import com.jovvi.mobile.common_mpp.Color
import com.jovvi.mobile.common_mpp.Serializable

data class TopicModel(
    val id: Long,
    val name: String,
    val colorStart: Color,
    val colorEnd: Color
) : Serializable {

    companion object {

        fun favouriteQuestions(localizedName: String): TopicModel {
            return TopicModel(
                id = -1L,
                name = localizedName,
                colorStart = Color(red = 0xbb, green = 0xa1, blue = 0xff),
                colorEnd = Color(red = 0xcf, green = 0xbd, blue = 0xff)
            )
        }
    }
}
