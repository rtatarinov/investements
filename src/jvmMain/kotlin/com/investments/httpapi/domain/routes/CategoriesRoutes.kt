package com.investments.httpapi.domain.routes

import com.investments.httpapi.domain.category.*
import com.investments.httpapi.domain.category.factory.CategoryViewFactory
import com.investments.httpapi.domain.category.repository.CategoryRepository
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
            val result = categories.findAll()

            call.respond(CategoryViewFactory().createList(result))
        }

        get("{id}") {
            val id = call.parameters["id"]
                ?: return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )

            val category =
                categories.findById(id) ?: return@get call.respondText(
                    "No category with id $id",
                    status = HttpStatusCode.NotFound
                )

            val result = CategoryViewFactory().createSingle(category)

            call.respond(result)
        }

        post {
            val payload = call.receive<Category>()
            val category = Category(payload.getName(), UUID.randomUUID())

            categories.save(category)
            call.respondText("Category stored correctly", status = HttpStatusCode.Accepted)
        }

        patch("{id}") {
            val id = call.parameters["id"] ?: return@patch call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val payload = call.receive<Category>()

            categories.save(payload)
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val category = categories.findById(id);

            if (category != null) {
                categories.remove(category)
                call.respondText("Category stored correctly", status = HttpStatusCode.Accepted)
            }
        }
    }
}

fun Application.registerCategoriesRoutes() {
    routing {
        categoriesRouting()
    }
}