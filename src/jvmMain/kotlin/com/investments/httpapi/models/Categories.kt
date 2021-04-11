package com.investments.httpapi.models

import java.util.*
import kotlinx.serialization.Serializable
import com.investments.httpapi.utils.UUIDSerializer

@Serializable
data class CategoryItem(
    val name: String,
    @Serializable(with = UUIDSerializer::class)
    val id: UUID
)

val categoryStorage = listOf(
    CategoryItem("First Category", UUID.randomUUID()),
    CategoryItem("Second Category", UUID.randomUUID()),
    CategoryItem("Third Category", UUID.randomUUID()),
)