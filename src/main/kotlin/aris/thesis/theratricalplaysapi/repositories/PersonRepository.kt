package aris.thesis.theratricalplaysapi.repositories

import aris.thesis.theratricalplaysapi.entities.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PersonRepository : JpaRepository<Person?, Int?>, JpaSpecificationExecutor<Person?>