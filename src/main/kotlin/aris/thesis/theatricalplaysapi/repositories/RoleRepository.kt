package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Int>