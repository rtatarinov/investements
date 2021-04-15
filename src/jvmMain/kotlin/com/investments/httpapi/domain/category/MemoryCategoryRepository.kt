package com.investments.httpapi.domain.category

class MemoryCategoryRepository(initialCategories: MutableList<Category>) : CategoryRepository {
    private val items = initialCategories;

    override fun add(item: Category) {
        this.items.add(item)
    }

    override fun getAll(): List<Category> {
        return this.items
    }

    override fun findById(id: String): Category? {
        return items.find {
            it.getId() == id
        }
    }

    override fun findByName(name: String): Category? {
        return items.find {
            it.getName() == name
        }
    }

    override fun findIndexById(id: String): Int {
        return this.items.indexOfFirst { it.getId() === id }
    }

    override fun updateItemById(id: String, payload: Category): Category? {
        val currentCategory = this.items.find { it.getId() == id }

        currentCategory?.setName(payload.getName())

        return currentCategory
    }

    override fun removeById(id: String) {
        val index = this.findIndexById(id)

        if (index != -1) {
            this.items.removeAt(index)
        }
    }
}