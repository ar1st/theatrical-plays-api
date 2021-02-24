package aris.thesis.theratricalplaysapi.repositories;

import aris.thesis.theratricalplaysapi.entities.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonsRepository extends JpaRepository<Persons, Integer>, JpaSpecificationExecutor<Persons> {

}