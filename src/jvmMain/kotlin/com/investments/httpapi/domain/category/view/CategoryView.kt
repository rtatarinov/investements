package com.investments.httpapi.domain.category.view

import com.investments.httpapi.domain.adapters.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CategoryView(
    private var name: String,
    @Serializable(with = UUIDSerializer::class)
    private val id: UUID
)