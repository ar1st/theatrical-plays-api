package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.System
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SystemRepository : JpaRepository<System, Int>