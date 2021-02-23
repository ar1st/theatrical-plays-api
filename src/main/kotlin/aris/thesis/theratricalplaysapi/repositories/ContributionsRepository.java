package aris.thesis.theratricalplaysapi.repositories;

import aris.thesis.theratricalplaysapi.entities.Contributions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContributionsRepository extends JpaRepository<Contributions, Integer>, JpaSpecificationExecutor<Contributions> {

}