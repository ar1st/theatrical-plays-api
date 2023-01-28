package aris.thesis.theatricalplaysapi.exceptions

abstract class RestException : RuntimeException() {

    override var cause: Throwable? = null

}