package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Int>, JpaSpecificationExecutor<Person> {

    @Query(value = "select persons.* " +
            "from persons inner join contributions on persons.ID = contributions.PeopleId " +
            "where contributions.ID = :contributionId", nativeQuery = true)
    fun findByContributionId(contributionId: Int): Person?
}