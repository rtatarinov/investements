package com.investments.httpapi.domain.category.request

import com.investments.httpapi.domain.category.Category
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class CategoryRequest(
    val name: String,
    val id: String? = null
) {
    @Contextual
    private val category = Category(name, if (id != null) UUID.fromString(id) else UUID.randomUUID())

    fun getCategory(): Category {
        return this.category
    }
}