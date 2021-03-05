package aris.thesis.theatricalplaysapi.exceptions

import aris.thesis.theatricalplaysapi.exceptions.error.RestError

abstract class RestException : RuntimeException  {

    override var cause: Throwable? = null

    constructor() : super()

    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)

    abstract fun status(): String

    abstract fun errorCount(): Int
    abstract fun typeOfErrorAt(index: Int): String
    abstract fun descriptionOfErrorAt(index: Int): String
    abstract fun causeOfErrorAt(index: Int): String

    fun toRestErrors(responseFactory: RestResponseFactory): List<RestError> {
        val errors: MutableList<RestError> = mutableListOf()

        for (i in 0 until errorCount())
        {
            val type = typeOfErrorAt(i)
            val description = descriptionOfErrorAt(i)
            val cause = causeOfErrorAt(i)

            val error = responseFactory.createError(type, description, cause)
            errors.add(error)
        }

        return errors.toList()
    }
}