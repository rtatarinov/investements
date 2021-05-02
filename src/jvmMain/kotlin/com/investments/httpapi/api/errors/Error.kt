package com.investments.httpapi.api.errors

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val message: String,
    val field: String,
)