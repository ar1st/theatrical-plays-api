package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.entities.Role
import aris.thesis.theatricalplaysapi.repositories.RoleRepository
import aris.thesis.theatricalplaysapi.services.types.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl: RoleService {
    @Autowired
    lateinit var roleRepository: RoleRepository

    override fun findByContribution(contributionId: Int): Role? {
        return roleRepository.findByContribution(contributionId)
    }
}