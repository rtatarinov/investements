package com.investments.httpapi.api.errors.exceptions

import com.investments.httpapi.api.errors.ErrorResponse
import io.ktor.http.*

abstract class ApiException : RuntimeException() {
    abstract fun response(): ErrorResponse
    abstract fun statusCode(): HttpStatusCode
}