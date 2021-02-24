package aris.thesis.theratricalplaysapi.repositories

import aris.thesis.theratricalplaysapi.entities.Contribution
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ContributionRepository : JpaRepository<Contribution?, Int?>, JpaSpecificationExecutor<Contribution?>