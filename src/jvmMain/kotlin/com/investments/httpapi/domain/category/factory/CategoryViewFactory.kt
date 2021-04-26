package com.investments.httpapi.domain.category.factory

import com.investments.httpapi.domain.category.view.CategoryView
import com.investments.httpapi.domain.entity.Category

interface CategoryViewFactory {
    fun createList(categories: List<Category>): List<CategoryView>
    fun createSingle(category: Category): CategoryView
}