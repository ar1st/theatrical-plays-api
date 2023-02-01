package aris.thesis.theatricalplaysapi.dtos.request

class CreateProductionRequest(
    val title: String, val description: String, val url: String, val producer: String,
    val mediaURL: String, val duration: String, val organizerId: Int
)