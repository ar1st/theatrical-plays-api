package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.services.proto.ModelService

interface ImageService: ModelService {

    fun getById(imageId: Int): Image?
}