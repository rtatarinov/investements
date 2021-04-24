package com.investments.httpapi.domain.category.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import kotlinx.serialization.Serializable

@Serializable
data class CategoryRequest(
    @NotNull(message = "Name must be not empty")
    @Max(255, message = "Name must be no more than 255 characters.")
    val name: String,
    @Pattern(
        regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$",
        message = "Invalid UUID"
    )
    val id: String? = null
)