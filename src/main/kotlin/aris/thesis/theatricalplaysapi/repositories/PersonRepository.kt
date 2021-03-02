package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PersonRepository : JpaRepository<Person, Int> {
    @Transactional
    @Query(value="select persons.ID, persons.Fullname, roles.Role " +
            "from persons inner join contributions on persons.ID = contributions.PeopleID " +
            "inner join production on production.ID = contributions.ProductionID " +
            "inner join roles on roles.ID = contributions.RoleID " +
            "where production.ID = :productionId", nativeQuery = true)
    fun findRoleByProduction(productionId: Int): List<Map<String,String>>



}