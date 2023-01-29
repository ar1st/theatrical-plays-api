package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.dtos.request.CreateImageRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.repositories.ImageRepository
import aris.thesis.theatricalplaysapi.repositories.PersonRepository
import aris.thesis.theatricalplaysapi.services.types.ImageService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ImageServiceImpl(val imageRepository: ImageRepository, val personRepository: PersonRepository): ImageService {

    override fun getAll(): List<Image> {
        return imageRepository.findAll()
    }

    override fun getByPersonId(personId: Int): Set<Image> {
        return imageRepository.findByPersonId(personId)
    }

    @Transactional
    override fun createImage(request: CreateImageRequest): EntityId? {
        val person = personRepository.findByIdOrNull(request.personId) ?: notFound("Person", request.personId.toString())

        val image = Image(request.imageURL, person.id, null)

        val savedImage = imageRepository.save(image)

        return EntityId(savedImage.id)
    }

    @Transactional
    override fun deleteImage(imageId: Int) {
        imageRepository.findByIdOrNull(imageId) ?: notFound("Image", imageId.toString())

        imageRepository.deleteById(imageId)
    }
}