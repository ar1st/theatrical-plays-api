package aris.thesis.theatricalplaysapi.exceptions

import org.springframework.http.HttpStatus

class UnreachableStatementException: RestException()  {

    override fun status(): String {
        return HttpStatus.BAD_REQUEST.name
    }

    override fun errorCount(): Int {
        return 1
    }

    override fun typeOfErrorAt(index: Int): String {
        return "Request.Unreachable"
    }

    override fun descriptionOfErrorAt(index: Int): String {
        return "Unreachable statement."
    }

    override fun causeOfErrorAt(index: Int): String {
        return "Assertion error. Unreachable statement was still executed."
    }
}