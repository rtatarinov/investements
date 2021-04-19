package com.investments.httpapi.domain.category

import org.koin.dsl.module
import java.util.*

val items = mutableListOf(
    Category("1 category", UUID.randomUUID()),
    Category("2 category", UUID.randomUUID()),
    Category("3 category", UUID.randomUUID()),
)

val categoriesModule = module {
    single<CategoryRepository> {
        MemoryCategoryRepository(items)
    }
    single { MemoryCategoryRepository(items) }
}