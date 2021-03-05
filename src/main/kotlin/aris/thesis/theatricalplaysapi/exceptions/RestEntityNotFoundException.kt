package aris.thesis.theatricalplaysapi.exceptions

import org.springframework.http.HttpStatus


class RestEntityNotFoundException(var type: String = "T", var id: String = "ID") : RestException() {

    constructor(entityInfo: RestEntityRegistration<*,*>, id: Any?): this(entityInfo.entityName, id.toString())

    override fun status(): String {
        return HttpStatus.NOT_FOUND.name
    }

    override fun errorCount(): Int {
        return 1
    }

    override fun typeOfErrorAt(index: Int): String {
        return "Request.Data.NotFound"
    }

    override fun descriptionOfErrorAt(index: Int): String {
        return "No entity found for provided identifier."
    }

    override fun causeOfErrorAt(index: Int): String {
        return "No matching entity of type $type and identifier $id was found."
    }
}