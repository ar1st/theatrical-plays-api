package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Venue
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VenueRepository : JpaRepository<Venue, Int>