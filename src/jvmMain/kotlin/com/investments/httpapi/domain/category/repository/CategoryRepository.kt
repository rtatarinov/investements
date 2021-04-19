package com.investments.httpapi.domain.category.repository

import com.investments.httpapi.domain.category.Category

interface CategoryRepository {
    fun findAll(): List<Category>
    fun findById(id: String): Category?
    fun save(item: Category)
    fun remove(item: Category)
}