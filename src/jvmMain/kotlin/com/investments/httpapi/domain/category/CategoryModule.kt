package com.investments.httpapi.domain.category

import com.investments.httpapi.domain.category.repository.CategoryRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.*

class CategoryModule {
    private val memoryItems = mutableListOf(
        Category("1 category", UUID.randomUUID()),
        Category("2 category", UUID.randomUUID()),
        Category("3 category", UUID.randomUUID()),
    )

    private fun getMemoryModule(): Module {
        return module {
            single<CategoryRepository> {
                MemoryCategoryRepository(memoryItems)
            }
            single { MemoryCategoryRepository(memoryItems) }
        }
    }

    fun get(): Module {
        return getMemoryModule()
    }
}