package com.investments.httpapi.domain.category.adapters

import com.investments.httpapi.domain.category.request.CategoryRequest
import com.investments.httpapi.domain.entity.Category

class CategoryModifier {
    fun modify(category: Category, request: CategoryRequest) {
        category.name = request.name
    }
}