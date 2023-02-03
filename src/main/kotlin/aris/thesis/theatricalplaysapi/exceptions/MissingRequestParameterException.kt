package aris.thesis.theatricalplaysapi.exceptions

class MissingRequestParameterException(val parameter: String): RestException()