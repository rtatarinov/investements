package com.investments.httpapi.domain.category.factory

import com.investments.httpapi.domain.category.view.CategoryView
import com.investments.httpapi.domain.entity.Category

class CategoryViewFactory {
    fun createList(categories: List<Category>): List<CategoryView> {
        return categories.map {
            this.createSingle(it)
        }
    }

    fun createSingle(category: Category): CategoryView {
        return CategoryView(category.name, category.id)
    }
}