package com.investments.httpapi.domain.category.request

import kotlinx.serialization.Serializable

@Serializable
data class CategoryRequest(
    val name: String,
    val id: String? = null
)