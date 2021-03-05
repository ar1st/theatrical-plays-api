package aris.thesis.theatricalplaysapi.rest

import aris.thesis.theatricalplaysapi.exceptions.error.RestError

interface RestResponse {
    fun withData(data: Any?)
    fun withError(error: RestError)
    fun withStatus(status:String)
}