package com.investments.httpapi.category.factory

import com.investments.httpapi.domain.category.factory.CategoryFactory
import com.investments.httpapi.domain.category.request.CategoryRequest
import com.investments.httpapi.domain.entity.Category
import java.util.*

class CategoryFactory : CategoryFactory {
    override fun create(categoryRequest: CategoryRequest): Category {
        val name = categoryRequest.name
        val id = if (categoryRequest.id != null) UUID.fromString(categoryRequest.id) else UUID.randomUUID()

        return Category(name, id)
    }
}