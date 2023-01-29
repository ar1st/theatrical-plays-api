package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.curators.DataCurator
import aris.thesis.theatricalplaysapi.dtos.request.CreateRoleRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.entities.Role
import aris.thesis.theatricalplaysapi.exceptions.error.illegalDelete
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.repositories.ContributionRepository
import aris.thesis.theatricalplaysapi.repositories.RoleRepository
import aris.thesis.theatricalplaysapi.services.types.RoleService
import aris.thesis.theatricalplaysapi.utils.doIfTrue
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoleServiceImpl(val roleRepository: RoleRepository, val contributionRepository: ContributionRepository) :
    RoleService {

    override fun getByContributionId(contributionId: Int): Role? {
        return roleRepository.findByContribution(contributionId)
    }

    @Transactional
    override fun createRole(request: CreateRoleRequest): EntityId {
        val roleByName = roleRepository.findByRole(request.role)
        val id = EntityId()

        if (roleByName == null) {
            DataCurator.curateRole(request.role)
            val role = roleRepository.save(Role(request.role))
            id.newId = role.id
        } else {
            id.existingId = roleByName.id
        }

        return id
    }

    @Transactional
    override fun deleteRole(roleId: Int) {
        roleRepository.findByIdOrNull(roleId) ?: notFound("Role", roleId.toString())

        val existsContribution = contributionRepository.existsByRoleID(roleId)

        existsContribution.doIfTrue { illegalDelete() }

        roleRepository.deleteById(roleId)
    }
}