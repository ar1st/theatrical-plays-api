package aris.thesis.theratricalplaysapi.repositories

import aris.thesis.theratricalplaysapi.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface RoleRepository : JpaRepository<Role?, Int?>, JpaSpecificationExecutor<Role?>