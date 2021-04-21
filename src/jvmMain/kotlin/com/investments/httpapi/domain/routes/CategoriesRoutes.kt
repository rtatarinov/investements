package com.investments.httpapi.domain.routes

import com.investments.httpapi.domain.category.factory.CategoryFactory
import com.investments.httpapi.domain.category.factory.CategoryViewFactory
import com.investments.httpapi.domain.category.repository.CategoryRepository
import com.investments.httpapi.domain.category.request.CategoryRequest
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.categoriesRouting() {
    val categories: CategoryRepository by inject()

    route(Routes.CATEGORIES.getApiRoute()) {
        get {
            val categoryView = CategoryViewFactory()
            val result = categoryView.createList(categories.findAll())

            call.respond(result)
        }

        get("{id}") {
            val categoryView = CategoryViewFactory()
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

            val result = categoryView.createSingle(category)

            call.respond(result)
        }

        post {
            val payload = call.receive<CategoryRequest>()
            val categoryRequest = CategoryRequest(payload.name)
            val category = CategoryFactory().create(categoryRequest)
            val categoryView = CategoryViewFactory().createSingle(category)

            categories.save(category)
            call.respond(status = HttpStatusCode.Created, categoryView)
        }

        patch("{id}") {
            val id = call.parameters["id"] ?: return@patch call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val payload = call.receive<CategoryRequest>()
            val category = categories.findById(id)

            if (category != null) {
                val categoryRequest = CategoryRequest(payload.name, id)
                val updatedCategory = CategoryFactory().create(categoryRequest)
                val categoryView = CategoryViewFactory().createSingle(updatedCategory)

                category.replaceWith(updatedCategory)
                call.respond(status = HttpStatusCode.Accepted, categoryView)
            } else {
                return@patch call.respondText(
                    "Category with this id doesn't exists",
                    status = HttpStatusCode.NotFound
                )
            }
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
            } else {
                return@delete call.respondText(
                    "Category with this id doesn't exists",
                    status = HttpStatusCode.NotFound
                )
            }
        }
    }
}

fun Application.registerCategoriesRoutes() {
    routing {
        categoriesRouting()
    }
}