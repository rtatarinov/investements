package com.investments.httpapi.api.errors

class ErrorFactory(
    val code: Int,
    val message: String,
) {
    fun create(): Error {
        return Error(code, message)
    }
}