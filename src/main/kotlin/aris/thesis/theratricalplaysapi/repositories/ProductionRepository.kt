package aris.thesis.theratricalplaysapi.repositories

import aris.thesis.theratricalplaysapi.entities.Production
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ProductionRepository : JpaRepository<Production?, Int?>, JpaSpecificationExecutor<Production?>