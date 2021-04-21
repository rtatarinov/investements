package com.investments.httpapi.domain.category

import java.util.*

data class Category(
    var name: String,
    val id: UUID
) {
    fun replaceWith(updatedCategory: Category) {
        this.name = updatedCategory.name
    }
}