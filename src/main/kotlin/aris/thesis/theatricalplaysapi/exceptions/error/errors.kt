package aris.thesis.theatricalplaysapi.exceptions.error

import aris.thesis.theatricalplaysapi.exceptions.InvalidFullNameException
import aris.thesis.theatricalplaysapi.exceptions.QueryParsingException
import aris.thesis.theatricalplaysapi.exceptions.RestEntityNotFoundException
import aris.thesis.theatricalplaysapi.exceptions.UnreachableStatementException

fun notFound(type: String = "T", id: String = "ID"): Nothing {
    throw RestEntityNotFoundException(type, id)
}

fun never(): Nothing {
    throw UnreachableStatementException()
}

fun wrongQuery(): Nothing {
    throw  QueryParsingException()
}

fun invalidFullName(fullName: String): Nothing {
    throw InvalidFullNameException(fullName)

}