package com.investments.httpapi.domain.category.factory

import com.investments.httpapi.domain.category.Category
import com.investments.httpapi.domain.category.view.CategoryView

class CategoryViewFactory {
    fun createList(categories: List<Category>): List<CategoryView> {
        return categories.map {
            CategoryView(it.getName(), it.getId())
        }
    }

    fun createSingle(category: Category): CategoryView {
        return CategoryView(category.getName(), category.getId())
    }
}