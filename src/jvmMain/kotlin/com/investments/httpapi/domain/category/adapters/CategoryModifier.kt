package com.investments.httpapi.domain.category.adapters

import com.investments.httpapi.domain.category.request.CategoryRequest
import com.investments.httpapi.domain.entity.Category

interface CategoryModifier {
    fun modify(category: Category, request: CategoryRequest)
}