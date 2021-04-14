package com.investments.httpapi.domain.category

interface CategoryRepository {
    fun add(item: Category)
    fun getAll(): List<Category>
    fun findById(id: String): Category?
    fun findByName(name: String): Category?
    fun removeById(id: String)
}