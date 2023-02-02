package aris.thesis.theatricalplaysapi.dtos.request

import org.springframework.format.annotation.DateTimeFormat
import java.util.Date

class CreateEventRequest(
    val venueId: Int,
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") val date: Date,
    val priceRange: String
)