package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.Venue
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VenueRepository : JpaRepository<Venue, Int>{

    @Query(value = "select venue.* " +
            "from venue inner join events on venue.ID = events.VenueID " +
            "where events.ID = :eventId", nativeQuery = true)
    fun findVenueByEventId(eventId: Int): Venue

    fun findByTitle(title: String): Venue?

}