package com.jovvi.mobile.business_category.di

import com.jovvi.mobile.business_category.repository.FavouriteQuestionRepository
import com.jovvi.mobile.business_category.repository.QuestionRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val questionBusinessModule = module {
    factory { QuestionRepository(Dispatchers.Default, get(), get()) }
    factory { FavouriteQuestionRepository(Dispatchers.Default, get(), get()) }
}
