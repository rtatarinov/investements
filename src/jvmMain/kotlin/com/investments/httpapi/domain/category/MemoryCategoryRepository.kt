package com.investments.httpapi.domain.category

import com.investments.httpapi.domain.category.repository.CategoryRepository
import java.util.*

class MemoryCategoryRepository(initialCategories: List<Category>) : CategoryRepository {
    private val items = initialCategories.toMutableList();

    private fun findIndexById(id: UUID): Int {
        return this.items.indexOfFirst { it.id.equals(id) }
    }

    override fun findAll(): List<Category> {
        return this.items.toList()
    }

    override fun findById(id: String): Category? {
        return items.find {
            it.id.equals(UUID.fromString(id))
        }
    }

    override fun save(item: Category) {
        this.items.add(item)
    }

    override fun remove(item: Category) {
        val index = this.findIndexById(item.id)

        if (index != -1) {
            this.items.removeAt(index)
        }
    }
}