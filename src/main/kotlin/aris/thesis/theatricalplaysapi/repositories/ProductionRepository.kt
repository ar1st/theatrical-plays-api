package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Production
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductionRepository : JpaRepository<Production, Int> {

    @Query(value="SELECT persons.* " +
            "FROM contributions inner join persons on persons.ID = contributions.PeopleID " +
            "where contributions.ID = :contributionId", nativeQuery = true)
    fun findByContribution(contributionId: Int): Production
}