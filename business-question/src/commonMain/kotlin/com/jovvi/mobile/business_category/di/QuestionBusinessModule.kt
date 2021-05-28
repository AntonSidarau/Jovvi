package com.jovvi.mobile.business_category.di

import com.jovvi.mobile.business_category.repository.QuestionRepository
import org.koin.dsl.module

val questionBusinessModule = module {
    factory { QuestionRepository(get(), get()) }
}