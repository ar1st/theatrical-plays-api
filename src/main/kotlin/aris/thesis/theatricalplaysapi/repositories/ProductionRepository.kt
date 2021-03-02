package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Production
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ProductionRepository : JpaRepository<Production, Int> {

    @Transactional
    @Query(value= "select production.ID, production.Title, production.Description, production.MediaURL " +
            "from persons inner join contributions on persons.ID = contributions.PeopleID " +
            "inner join production on production.ID = contributions.ProductionID " +
            "inner join roles on roles.ID = contributions.RoleID " +
            "where persons.ID = :personId", nativeQuery = true)
    fun findByPersonId(personId: Int) : List<Map<String, String>>

}