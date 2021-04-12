package com.investments.httpapi.models

import com.investments.httpapi.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CategoryItem(
    val name: String,
    @Serializable(with = UUIDSerializer::class)
    val id: UUID
)

val categoriesStorage = mutableListOf(
    CategoryItem("First Category", UUID.randomUUID()),
    CategoryItem("Second Category", UUID.randomUUID()),
    CategoryItem("Third Category", UUID.randomUUID()),
)