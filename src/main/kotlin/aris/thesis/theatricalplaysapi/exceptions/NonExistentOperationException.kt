package aris.thesis.theatricalplaysapi.exceptions

import org.springframework.http.HttpStatus

class NonExistentOperationException(private val operation: Char): RestException() {
    override fun status(): String {
        return HttpStatus.BAD_REQUEST.name
    }

    override fun errorCount(): Int {
        return 1
    }

    override fun typeOfErrorAt(index: Int): String {
        return "Operation.NonExistent"
    }

    override fun descriptionOfErrorAt(index: Int): String {
        return "No such operation."
    }

    override fun causeOfErrorAt(index: Int): String {
        return "Operation error. $operation is not a recognized operation."
    }
}