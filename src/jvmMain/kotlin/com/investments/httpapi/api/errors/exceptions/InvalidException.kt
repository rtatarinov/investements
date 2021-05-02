package com.investments.httpapi.api.errors.exceptions

import com.investments.httpapi.api.errors.Error
import com.investments.httpapi.api.errors.ErrorResponse
import io.ktor.http.*

class InvalidException(private val errors: List<Error>) : ApiException() {
    private fun transformErrors(): Map<String, String> = errors.associate {
        it.let { (message, field) -> field to message }
    }

    override fun response() = ErrorResponse.create(
        HttpStatusCode.BadRequest.value,
        this.transformErrors() as MutableMap<String, String>
    )

    override fun statusCode() = HttpStatusCode.BadRequest
}