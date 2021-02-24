package aris.thesis.theratricalplaysapi.repositories

import aris.thesis.theratricalplaysapi.entities.ChangeLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChangeLogRepository : JpaRepository<ChangeLog, Int>