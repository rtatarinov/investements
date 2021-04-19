package com.investments.httpapi.domain.routes

import com.investments.httpapi.domain.category.Category
import com.investments.httpapi.domain.category.CategoryRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import java.util.*

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
            val result =
                categories.findById(id) ?: return@get call.respondText(
                    "No category with id $id",
                    status = HttpStatusCode.NotFound
                )

            call.respond(result)
        }

        post {
            val payload = call.receive<Category>()
            val category = Category(payload.getName(), UUID.randomUUID())

            categories.add(category)
            call.respondText("Category stored correctly", status = HttpStatusCode.Accepted)
        }

        patch("{id}") {
            val id = call.parameters["id"] ?: return@patch call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            // Kotlin doesn't support partial classes. What type here?
            val payload = call.receive<Category>()
            val updatedCategory = categories.updateItemById(id, payload)

            if (updatedCategory != null) {
                call.respond(updatedCategory)
            }
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            categories.removeById(id)
            call.respondText("Category stored correctly", status = HttpStatusCode.Accepted)
        }
    }
}

fun Application.registerCategoriesRoutes() {
    routing {
        categoriesRouting()
    }
}