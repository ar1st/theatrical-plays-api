package aris.thesis.theratricalplaysapi.repositories

import aris.thesis.theratricalplaysapi.entities.Organizer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface OrganizerRepository : JpaRepository<Organizer?, Int?>, JpaSpecificationExecutor<Organizer?>