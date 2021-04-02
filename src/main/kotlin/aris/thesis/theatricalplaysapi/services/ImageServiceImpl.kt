package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.repositories.ImageRepository
import aris.thesis.theatricalplaysapi.services.types.ImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ImageServiceImpl: ImageService {
    //repos
    @Autowired
    lateinit var imageRepository: ImageRepository

    override fun getById(imageId: Int): Image? {
        return imageRepository.findByIdOrNull(imageId)
    }
}