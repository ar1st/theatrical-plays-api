package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Person
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    @Query(value = "select p.*, r.Role " +
                "from persons p inner join contributions c on p.ID=c.PeopleID " +
                "inner join roles r on r.ID= c.RoleID " +
                "where r.Role like :value",
            countQuery = "select count(*) " +
                "from persons p inner join contributions c on p.ID=c.PeopleID " +
                "inner join roles r on r.ID= c.RoleID " +
                "where r.Role like :value",
            nativeQuery = true)
    fun findPeopleByRole(value: String, pageable: Pageable): Page<Person>
}