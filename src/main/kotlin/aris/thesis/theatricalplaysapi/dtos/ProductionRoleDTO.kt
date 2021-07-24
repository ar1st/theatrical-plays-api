package aris.thesis.theatricalplaysapi.dtos

import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.entities.Role

class ProductionRoleDTO(production: Production, role: Role): DataTransferObject {
    val productionId = production.id
    val title = production.title
    val url = production.url
    val producer = production.producer
    val mediaURL = production.mediaURL
    val duration = production.mediaURL
    val description = production.description
    val role = role.role


    constructor() : this(Production(),Role())
}