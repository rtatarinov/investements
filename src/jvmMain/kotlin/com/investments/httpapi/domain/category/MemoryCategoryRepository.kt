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

    override fun removeById(id: String) {
        TODO("Not yet implemented")
    }
}