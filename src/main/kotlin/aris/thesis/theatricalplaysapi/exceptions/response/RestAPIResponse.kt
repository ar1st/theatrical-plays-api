package aris.thesis.theatricalplaysapi.exceptions.response

import aris.thesis.theatricalplaysapi.exceptions.error.RestAPIError
import aris.thesis.theatricalplaysapi.exceptions.error.RestError


data class RestAPIResponse(var data: Any? = null,
                           var errors: List<RestAPIError>? = null,
                           var status: String? = null): RestResponse
{

    override fun withData(data: Any?) {
        this.data = data
    }

    override fun withError(error: RestError) {
        val mutableErrors = errors?.toMutableList() ?: mutableListOf()
        mutableErrors.add(error as RestAPIError)

        errors = mutableErrors
    }

    override fun withStatus(status: String) {
        this.status = status
    }
}