package aris.thesis.theatricalplaysapi.exceptions.error

data class ApiError(var type: String, var description: String, var cause: String): RestError {

}