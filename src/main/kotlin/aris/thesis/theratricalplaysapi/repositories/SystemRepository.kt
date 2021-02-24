package aris.thesis.theratricalplaysapi.repositories

import aris.thesis.theratricalplaysapi.entities.System
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface SystemRepository : JpaRepository<System, Int>