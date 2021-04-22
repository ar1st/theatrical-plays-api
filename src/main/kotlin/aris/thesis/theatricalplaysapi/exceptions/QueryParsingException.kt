package aris.thesis.theatricalplaysapi.exceptions

import org.springframework.http.HttpStatus

class QueryParsingException: RestException() {
    override fun status(): String {
        return HttpStatus.BAD_REQUEST.name
    }

    override fun errorCount(): Int {
        return 1
    }

    override fun typeOfErrorAt(index: Int): String {
        return "Query.Error"
    }

    override fun descriptionOfErrorAt(index: Int): String {
        return "Invalid query."
    }

    override fun causeOfErrorAt(index: Int): String {
        return "Query error. Query not in correct formation."
    }
}