package com.investments.httpapi.domain.category

import com.investments.httpapi.domain.category.repository.CategoryRepository

class MemoryCategoryRepository(initialCategories: List<Category>) : CategoryRepository {
    private val items = initialCategories.toMutableList();

    private fun findIndexById(id: String): Int {
        return this.items.indexOfFirst { it.getId().toString() === id }
    }

    private fun updateItemById(id: String, payload: Category): Category? {
        val currentCategory = this.items.find { it.getId().toString() == id }

        currentCategory?.setName(payload.getName())

        return currentCategory
    }

    override fun findAll(): List<Category> {
        return this.items.toList()
    }

    override fun findById(id: String): Category? {
        return items.find {
            it.getId().toString() == id
        }
    }

    override fun save(item: Category) {
        val category = this.findById(item.getId().toString())

        if (category == null) {
            this.items.add(item)
        } else {
            this.updateItemById(category.getId().toString(), category)
        }
    }

    override fun remove(item: Category) {
        val index = this.findIndexById(item.getId().toString())

        if (index != -1) {
            this.items.removeAt(index)
        }
    }
}