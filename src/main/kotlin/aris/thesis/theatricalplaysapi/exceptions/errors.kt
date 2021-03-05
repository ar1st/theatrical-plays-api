package aris.thesis.theatricalplaysapi.exceptions

import java.lang.IllegalArgumentException

fun notFound(type: String = "T", id: String = "ID"): Nothing {
    throw RestEntityNotFoundException(type, id)
}