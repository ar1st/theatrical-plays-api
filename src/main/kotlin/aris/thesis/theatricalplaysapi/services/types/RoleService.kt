package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.dtos.request.CreateRoleRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.entities.Role
import aris.thesis.theatricalplaysapi.services.proto.ModelService

interface RoleService: ModelService {

    fun getByContributionId(contributionId: Int): Role?

    fun createRole(request: CreateRoleRequest): EntityId

    fun deleteRole(roleId: Int)
}