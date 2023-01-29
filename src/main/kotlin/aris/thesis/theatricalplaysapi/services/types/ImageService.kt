package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.dtos.request.CreateImageRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.services.proto.ModelService

interface ImageService: ModelService {

    fun getAll(): List<Image>

    fun getByPersonId(personId: Int): Set<Image>

    fun createImage(request: CreateImageRequest): EntityId?

    fun deleteImage(imageId: Int)
}