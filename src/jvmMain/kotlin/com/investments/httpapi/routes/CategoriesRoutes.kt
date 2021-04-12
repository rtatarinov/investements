package com.investments.httpapi.routes

import com.investments.httpapi.models.CategoryItem
import com.investments.httpapi.models.categoriesStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.categoriesRouting() {
    route(Routes.CATEGORIES.getApiRoute()) {
        get {
            if (categoriesStorage.isNotEmpty()) {
                call.respond(categoriesStorage)
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
                categoriesStorage.find { it.id.toString() == id } ?: return@get call.respondText(
                    "No customer with id $id",
                    status = HttpStatusCode.NotFound
                )

            call.respond(category)
        }

        post {
            val category = call.receive<CategoryItem>()

            // TODO - This shouldn't really be done in production as
            // we should be accessing a mutable list in a thread-safe manner.
            // However, in production code we wouldn't be using mutable lists as a database!
            categoriesStorage.add(category)
            call.respondText("Category stored correctly", status = HttpStatusCode.Accepted)
        }

        patch("{id}") {
            // Implement patch method of category here
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

            if (categoriesStorage.removeIf { it.id.toString() == id }) {
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}

fun Application.registerCategoriesRoutes() {
    routing {
        categoriesRouting()
    }
}