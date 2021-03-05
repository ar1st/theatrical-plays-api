package aris.thesis.theatricalplaysapi.exceptions.response

import aris.thesis.theatricalplaysapi.exceptions.RestResponseFactory
import aris.thesis.theatricalplaysapi.exceptions.error.RestAPIError
import aris.thesis.theatricalplaysapi.exceptions.error.RestError
import org.springframework.stereotype.Component

@Component
class RestResponseFactoryImpl: RestResponseFactory {


    override fun createResponse(): RestResponse {
        return RestAPIResponse()
    }

    override fun createError(type: String, description: String, cause: String): RestError {
        return RestAPIError(type, description, cause)
    }

    override fun createErrors(exception: Exception): List<RestError> {
        val unknown = "Unknown"

        return listOf(
            RestAPIError(exception::class.java.simpleName,
                exception.message ?: unknown,
                exception.cause?.message ?: unknown)
        )
    }
}