package com.investments.httpapi.routes

import com.investments.httpapi.models.categoryStorage
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.routing

fun Route.listCategoryRoute() {
    get("/category") {
        if (categoryStorage.isNotEmpty()) {
            call.respond(categoryStorage)
        } else {
            call.respondText("No categories found", status = HttpStatusCode.NotFound)
        }
    }
}

fun Application.registerCategoriesRoutes() {
    routing {
        listCategoryRoute()
    }
}