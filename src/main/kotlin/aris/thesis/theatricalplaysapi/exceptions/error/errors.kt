package aris.thesis.theatricalplaysapi.exceptions.error

import aris.thesis.theatricalplaysapi.exceptions.RestEntityNotFoundException

fun notFound(type: String = "T", id: String = "ID"): Nothing {
    throw RestEntityNotFoundException(type, id)
}