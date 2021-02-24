package aris.thesis.theratricalplaysapi.repositories

import aris.thesis.theratricalplaysapi.entities.System
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface SystemRepository : JpaRepository<System?, Int?>, JpaSpecificationExecutor<System?>