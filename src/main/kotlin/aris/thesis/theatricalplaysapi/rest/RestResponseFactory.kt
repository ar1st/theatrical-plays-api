package aris.thesis.theatricalplaysapi.rest

import aris.thesis.theatricalplaysapi.exceptions.error.RestError

interface RestResponseFactory
{
    fun createResponse(): RestResponse
    fun createError(type:String,description:String,cause:String): RestError
    fun createErrors(exception:Exception): List<RestError>
}
