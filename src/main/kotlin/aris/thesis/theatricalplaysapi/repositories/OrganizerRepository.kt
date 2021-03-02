package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Organizer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizerRepository : JpaRepository<Organizer, Int>