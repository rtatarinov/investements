package com.investments.httpapi.config

import com.investments.httpapi.category.adapters.CategoryModifier
import com.investments.httpapi.category.factory.CategoryFactory
import com.investments.httpapi.category.factory.CategoryViewFactory
import com.investments.httpapi.category.repository.CategoryRepository
import com.investments.httpapi.domain.entity.Category
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.*

class CategoryConfig {
    private val memoryItems = mutableListOf(
        Category("1 category", UUID.randomUUID()),
        Category("2 category", UUID.randomUUID()),
        Category("3 category", UUID.randomUUID()),
    )

    fun getCategoryModule(): Module {
        return module {
            single<com.investments.httpapi.domain.category.repository.CategoryRepository> {
                CategoryRepository(memoryItems)
            }
            single { CategoryRepository(memoryItems) }
        }
    }

    fun getCategoryFactory(): Module {
        return module {
            single<com.investments.httpapi.domain.category.factory.CategoryFactory> {
                CategoryFactory()
            }
            single { CategoryFactory() }
        }
    }

    fun getCategoryViewFactory(): Module {
        return module {
            single<com.investments.httpapi.domain.category.factory.CategoryViewFactory> {
                CategoryViewFactory()
            }
            single {
                CategoryViewFactory()
            }
        }
    }

    fun getCategoryModifier(): Module {
        return module {
            single<com.investments.httpapi.domain.category.adapters.CategoryModifier> {
                CategoryModifier()
            }
            single {
                CategoryModifier()
            }
        }
    }

}