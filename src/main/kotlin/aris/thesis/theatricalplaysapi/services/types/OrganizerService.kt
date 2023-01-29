package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.dtos.request.CreateOrganizerRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.services.proto.ModelService

interface OrganizerService: ModelService {

    fun deleteOrganizer(organizerId: Int)

    fun creteOrganizer(request: CreateOrganizerRequest): EntityId
}