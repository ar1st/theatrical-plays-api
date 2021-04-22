package aris.thesis.theatricalplaysapi.exceptions.error

@Suppress("unused")
data class ApiError(var type: String, var description: String, var cause: String): RestError