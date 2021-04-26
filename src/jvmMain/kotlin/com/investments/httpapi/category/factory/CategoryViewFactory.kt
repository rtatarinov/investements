package com.investments.httpapi.category.factory

import com.investments.httpapi.domain.category.factory.CategoryViewFactory
import com.investments.httpapi.domain.category.view.CategoryView
import com.investments.httpapi.domain.entity.Category

class CategoryViewFactory : CategoryViewFactory {
    override fun createList(categories: List<Category>): List<CategoryView> {
        return categories.map {
            this.createSingle(it)
        }
    }

    override fun createSingle(category: Category): CategoryView {
        return CategoryView(category.name, category.id)
    }
}