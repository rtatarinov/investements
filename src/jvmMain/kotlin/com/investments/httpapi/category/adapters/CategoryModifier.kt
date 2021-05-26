package com.investments.httpapi.category.adapters

import com.investments.httpapi.domain.category.adapters.CategoryModifier
import com.investments.httpapi.domain.category.request.CategoryRequest
import com.investments.httpapi.domain.entity.Category

class CategoryModifier : CategoryModifier {
    override fun modify(category: Category, request: CategoryRequest) {
        category.name = request.name
    }
}