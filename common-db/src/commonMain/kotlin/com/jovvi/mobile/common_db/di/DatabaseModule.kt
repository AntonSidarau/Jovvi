package com.jovvi.mobile.common_db.di

import com.jovvi.mobile.common_db.JovviDatabase
import com.jovvi.mobile.common_db.dao.*
import org.koin.dsl.module

val databaseModule = module {
    single { JovviDatabase(get()) }

    factory<CategoryDao> {
        val db: JovviDatabase = get()
        DefaultCategoryDao(db.categoryQueries)
    }

    factory<TopicDao> {
        val db: JovviDatabase = get()
        DefaultTopicDao(db.topicQueries)
    }

    factory<QuestionDao> {
        val db: JovviDatabase = get()
        DefaultQuestionDao(db.questionQueries)
    }

    factory<FavouriteQuestionDao> {
        val db: JovviDatabase = get()
        DefaultFavouriteQuestionDao(db.favouriteQuestionQueries)
    }
}