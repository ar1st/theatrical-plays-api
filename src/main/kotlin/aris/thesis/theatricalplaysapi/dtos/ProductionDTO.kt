package aris.thesis.theatricalplaysapi.dtos

import aris.thesis.theatricalplaysapi.entities.Production

class ProductionDTO(production: Production): DataTransferObject {
    val id = production.id
    val title = production.title
    val url = production.url
    val producer = production.producer
    val mediaURL = production.mediaURL
    val duration = production.duration
    val description = production.description
}