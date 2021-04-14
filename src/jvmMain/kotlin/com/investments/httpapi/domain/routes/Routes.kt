package com.investments.httpapi.domain.routes

enum class Routes(private val route: String) {
    CATEGORIES("/categories");

    private val apiPrefix = "/api"


    fun getApiRoute(): String {
        return "$apiPrefix/$route"
    }
}