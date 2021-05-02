package com.investments.httpapi.api.errors

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: Int,
    val errors: Map<String, String>,
) {
    companion object {
        fun create(code: Int, errors: Map<String, String>) = ErrorResponse(code = code, errors = errors)
    }
}