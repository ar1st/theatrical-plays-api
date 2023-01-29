package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.constants.RestPathConstants
import aris.thesis.theatricalplaysapi.dtos.request.CreateRoleRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.services.types.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    RestPathConstants.REST_PATH_ROLES,
    produces = [MediaType.APPLICATION_JSON_VALUE + RestPathConstants.MEDIA_TYPE_UTF_8])
class RoleController(val roleService: RoleService) {

    @PostMapping
    fun createRole(@RequestBody request: CreateRoleRequest): ApiResponse<EntityId> {
        val id = roleService.createRole(request)

        return ApiResponse(id, null, HttpStatus.OK.name)
    }

    @DeleteMapping("/{roleId}")
    fun deleteImage(@PathVariable roleId: Int): ApiResponse<String?> {
        roleService.deleteRole(roleId)

        return ApiResponse(status = HttpStatus.OK.name)
    }



}