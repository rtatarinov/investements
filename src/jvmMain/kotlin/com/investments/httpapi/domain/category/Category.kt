package com.investments.httpapi.domain.category

import java.util.*

data class Category(
    private var name: String,
    private val id: UUID
) {
    fun getId(): UUID {
        return this.id
    }

    fun getName(): String {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }
}