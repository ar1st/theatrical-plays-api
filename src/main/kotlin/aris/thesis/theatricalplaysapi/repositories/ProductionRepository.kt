package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Production
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductionRepository : JpaRepository<Production, Int>, JpaSpecificationExecutor<Production> {

    @Query(value="SELECT production.* " +
            "FROM contributions inner join production on production.ID = contributions.ProductionID " +
            "where contributions.ID = :contributionId", nativeQuery = true)
    fun findByContribution(contributionId: Int): Production
}