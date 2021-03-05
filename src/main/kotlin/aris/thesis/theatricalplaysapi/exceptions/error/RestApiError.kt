package aris.thesis.theatricalplaysapi.exceptions.error

data class RestAPIError(var type: String, var description: String, var cause: String): RestError