package com.investments.httpapi.domain.category.factory

import com.investments.httpapi.domain.category.Category
import com.investments.httpapi.domain.category.request.CategoryRequest
import java.util.*

class CategoryFactory {
    fun create(categoryRequest: CategoryRequest): Category {
        val name = categoryRequest.name
        val id = if (categoryRequest.id != null) UUID.fromString(categoryRequest.id) else UUID.randomUUID()

        return Category(name, id)
    }
}