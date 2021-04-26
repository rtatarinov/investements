package com.investments.httpapi.config

import com.investments.httpapi.domain.entity.Category
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.*

class ManualConfig {
    private val memoryItems = mutableListOf(
        Category("1 category", UUID.randomUUID()),
        Category("2 category", UUID.randomUUID()),
        Category("3 category", UUID.randomUUID()),
    )

    fun getCategoryModule(): Module {
        return module {
            single<com.investments.httpapi.domain.category.repository.CategoryRepository> {
                com.investments.httpapi.repository.CategoryRepository(memoryItems)
            }
            single { com.investments.httpapi.repository.CategoryRepository(memoryItems) }
        }
    }
}