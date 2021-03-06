package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Production
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductionRepository : JpaRepository<Production, Int> {

    @Query(value="select production.* " +
            "from production inner join contributions on production.ID = contributions.ProductionID " +
            "where contributions.ID = :contributionId", nativeQuery = true)
    fun findByContribution(contributionId: Int): Production

}