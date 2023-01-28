package aris.thesis.theatricalplaysapi.exceptions

class RestEntityNotFoundException(var type: String = "T", var id: String = "ID") : RestException()