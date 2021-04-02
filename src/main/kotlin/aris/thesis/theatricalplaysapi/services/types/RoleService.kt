package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Role
import aris.thesis.theatricalplaysapi.services.proto.ModelService

interface RoleService: ModelService {

    fun getByContributionId(contributionId: Int): Role?
}