package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.ChangeLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChangeLogRepository : JpaRepository<ChangeLog, Int>