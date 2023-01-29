package aris.thesis.theatricalplaysapi.dtos.request

import aris.thesis.theatricalplaysapi.dtos.DataTransferObject

class CreateOrganizerRequest(
    val name: String,
    val address: String,
    val town: String,
    val postcode: String,
    val phone: String,
    val email: String,
    val afm: String,
    val doy: String
) : DataTransferObject