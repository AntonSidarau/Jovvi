package com.jovvi.mobile.business_category.di

import com.jovvi.mobile.business_category.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val categoryBusinessModule = module {
    factory { CategoryRepository(Dispatchers.Default, get()) }
}
