package aris.thesis.theatricalplaysapi.exceptions.error

import aris.thesis.theatricalplaysapi.exceptions.*

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

fun illegalDelete(): Nothing {
    throw IllegalDeleteException()
}

fun missingParameter(parameter: String): Nothing {
    throw MissingRequestParameterException(parameter)
}