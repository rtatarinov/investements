package com.investments.httpapi.domain.category.factory

import com.investments.httpapi.domain.category.request.CategoryRequest
import com.investments.httpapi.domain.entity.Category

interface CategoryFactory {
    fun create(categoryRequest: CategoryRequest): Category
}