package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Contribution
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContributionRepository : JpaRepository<Contribution, Int> {

    fun findByPeopleID(personId: Int, pageable:Pageable): Page<Contribution>

    fun findByProductionID(productionId: Int): List<Contribution>
}