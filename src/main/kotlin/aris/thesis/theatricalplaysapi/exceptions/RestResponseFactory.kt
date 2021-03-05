package aris.thesis.theatricalplaysapi.exceptions

import aris.thesis.theatricalplaysapi.exceptions.error.RestError
import aris.thesis.theatricalplaysapi.exceptions.response.RestResponse

interface RestResponseFactory
{
    fun createResponse(): RestResponse
    fun createError(type:String,description:String,cause:String): RestError
    fun createErrors(exception:Exception): List<RestError>
}
