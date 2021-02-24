package aris.thesis.theratricalplaysapi.repositories;

import aris.thesis.theratricalplaysapi.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VenueRepository extends JpaRepository<Venue, Integer>, JpaSpecificationExecutor<Venue> {

}