package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.curators.DataCurator
import aris.thesis.theatricalplaysapi.dtos.request.CreateOrganizerRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.entities.Organizer
import aris.thesis.theatricalplaysapi.exceptions.error.illegalDelete
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.repositories.OrganizerRepository
import aris.thesis.theatricalplaysapi.repositories.ProductionRepository
import aris.thesis.theatricalplaysapi.services.types.OrganizerService
import aris.thesis.theatricalplaysapi.utils.doIfTrue
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrganizerServiceImpl(
    val organizerRepository: OrganizerRepository,
    val productionRepository: ProductionRepository
) : OrganizerService {

    override fun deleteOrganizer(organizerId: Int) {
        organizerRepository.findByIdOrNull(organizerId) ?: notFound("Organizer", organizerId.toString())
        productionRepository.existsByOrganizerID(organizerId).doIfTrue { illegalDelete() }

        organizerRepository.deleteById(organizerId)
    }

    override fun creteOrganizer(request: CreateOrganizerRequest): EntityId {
        val organizerByAfm = organizerRepository.findByAfm(request.afm)
        val id = EntityId()

        if (organizerByAfm == null) {
            DataCurator.curateFullName(request.name)
            val venue = organizerRepository.save(
                Organizer(
                    request.name,
                    request.address,
                    request.town,
                    request.postcode,
                    request.phone,
                    request.email,
                    request.doy,
                    request.afm
                )
            )
            id.newId = venue.id
        } else {
            id.existingId = organizerByAfm.id
            organizerByAfm.name =  request.name
            organizerByAfm.address =  request.address
            organizerByAfm.town =  request.town
            organizerByAfm.postcode =  request.postcode
            organizerByAfm.phone =  request.phone
            organizerByAfm.email =  request.email
            organizerByAfm.doy =  request.doy
        }

        return id
    }
}