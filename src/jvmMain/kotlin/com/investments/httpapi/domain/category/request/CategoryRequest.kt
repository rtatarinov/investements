package com.investments.httpapi.domain.category.request

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.matches
import org.valiktor.validate

@Serializable
data class CategoryRequest(
    val name: String,
    val id: String? = null
) {
    init {
        validate(this) {
            validate(CategoryRequest::name).isNotBlank()
            validate(CategoryRequest::id).matches("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}\$".toRegex())
        }
    }
}