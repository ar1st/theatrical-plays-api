package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.constants.RestPathConstants
import aris.thesis.theatricalplaysapi.dtos.request.CreateImageRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.security.permission.IsAdmin
import aris.thesis.theatricalplaysapi.services.types.ImageService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    RestPathConstants.REST_PATH_IMAGES,
    produces = [MediaType.APPLICATION_JSON_VALUE + RestPathConstants.MEDIA_TYPE_UTF_8])
class ImageController(val imageService: ImageService) {

    @IsAdmin
    @PostMapping
    fun createImage(@RequestBody request: CreateImageRequest): ApiResponse<EntityId> {
        val id = imageService.createImage(request)

        return ApiResponse(id, null, HttpStatus.OK.name)
    }

    @IsAdmin
    @DeleteMapping("/{imageId}")
    fun deleteImage(@PathVariable imageId: Int): ApiResponse<String?> {
        imageService.deleteImage(imageId)

        return ApiResponse(status = HttpStatus.OK.name)
    }



}