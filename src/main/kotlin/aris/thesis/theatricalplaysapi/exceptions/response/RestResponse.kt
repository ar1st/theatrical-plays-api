package aris.thesis.theatricalplaysapi.exceptions.response

import aris.thesis.theatricalplaysapi.exceptions.error.RestError

interface RestResponse {
    fun withData(data: Any?)
    fun withError(error: RestError)
    fun withStatus(status:String)
}