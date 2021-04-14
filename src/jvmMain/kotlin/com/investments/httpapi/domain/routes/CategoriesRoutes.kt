package com.investments.httpapi.domain.routes

import com.investments.httpapi.domain.category.Category
import com.investments.httpapi.domain.category.CategoryRepository
import com.investments.httpapi.domain.category.MemoryCategoryRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import java.util.*

val items = mutableListOf(
    Category("1 category", UUID.randomUUID()),
    Category("2 category", UUID.randomUUID()),
    Category("3 category", UUID.randomUUID()),
)

val categoriesModule = module {
    single<CategoryRepository> {
        MemoryCategoryRepository(items)
    }
    single { MemoryCategoryRepository(items) }
}

fun Route.categoriesRouting() {
    val categories: CategoryRepository by inject()

    route(Routes.CATEGORIES.getApiRoute()) {
        get {
            if (categories.getAll().isNotEmpty()) {
                call.respond(categories.getAll())
            } else {
                call.respondText("No categories found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val category =
                categories.findById(id) ?: return@get call.respondText(
                    "No customer with id $id",
                    status = HttpStatusCode.NotFound
                )

            call.respond(category)
        }

        post {
            val category = call.receive<Category>()

            categories.add(category)
            call.respondText("Category stored correctly", status = HttpStatusCode.Accepted)
        }

        patch("{id}") {
            // Implement patch method of category here
        }

        delete("{id}") {
            // Implement delete method of category here
        }
    }
}

fun Application.registerCategoriesRoutes() {
    routing {
        categoriesRouting()
    }
}