package com.investments.httpapi.routes

import com.investments.httpapi.domain.category.adapters.CategoryModifier
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
import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.toMessage
import java.util.*

fun Route.categoriesRouting() {
    val categories: CategoryRepository by inject()

    route(Routes.CATEGORIES.getApiRoute()) {
        get {
            val categoriesView = CategoryViewFactory().createList(categories.findAll())

            call.respond(categoriesView)
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

            val categoryView = CategoryViewFactory().createSingle(category)

            call.respond(categoryView)
        }

        post {
            try {
                val payload = call.receive<CategoryRequest>()
                val category = CategoryFactory().create(payload)
                val categoryView = CategoryViewFactory().createSingle(category)

                categories.save(category)
                call.respond(status = HttpStatusCode.Created, categoryView)
            } catch (exception: ConstraintViolationException) {
                exception.constraintViolations.map { it.toMessage(locale = Locale.ENGLISH) }
                    .map { "${it.property}: ${it.message}" }
                    .forEach(::println)
            }
        }

        patch("{id}") {
            val id = call.parameters["id"] ?: return@patch call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val payload = call.receive<CategoryRequest>()
            val category = categories.findById(id)
                ?: return@patch call.respondText(
                    "Category with this id doesn't exists",
                    status = HttpStatusCode.NotFound
                )

            CategoryModifier().modify(category, payload)
            call.respond(status = HttpStatusCode.OK, CategoryViewFactory().createSingle(category))
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val category = categories.findById(id) ?: return@delete call.respondText(
                "Category with this id doesn't exists",
                status = HttpStatusCode.NotFound
            );

            categories.remove(category)
            call.response.status(HttpStatusCode.NoContent)
        }
    }
}

fun Application.registerCategoriesRoutes() {
    routing {
        categoriesRouting()
    }
}