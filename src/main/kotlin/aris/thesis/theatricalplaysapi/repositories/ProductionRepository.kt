package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Production
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    @Query(value="select DISTINCT production.* " +
            "from production inner join events on production.ID = events.ProductionID " +
            "order by events.DateEvent DESC",
            countQuery = "select count(*) " +
                    "from production inner join events on production.ID = events.ProductionID " +
                    "order by events.DateEvent DESC",
            nativeQuery = true)
    fun findLatestProductions(pageable: Pageable): Page<Production>

    @Query(value ="select DISTINCT production.* " +
            "from production inner join events on production.ID = events.ProductionID " +
            "inner join venue on events.VenueID = venue.ID " +
            "where venue.ID = :venueId",
            countQuery = "select count(*) " +
                    "from production inner join events on production.ID = events.ProductionID " +
                    "inner join venue on events.VenueID = venue.ID " +
                    "where venue.ID = :venueId",
            nativeQuery = true)
    fun findByVenueId(venueId: Int, pageable: Pageable): Page<Production>

    fun existsByOrganizerID(organizerId: Int): Boolean
}