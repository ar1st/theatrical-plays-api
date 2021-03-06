package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Int> {

    @Query(value = "select roles.* " +
            "from roles inner join contributions on roles.ID = contributions.RoleID " +
            "where contributions.ID = :contributionId", nativeQuery = true)
    fun findByContribution(contributionId: Int): Role
}