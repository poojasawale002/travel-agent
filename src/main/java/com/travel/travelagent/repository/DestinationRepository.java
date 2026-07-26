package com.travel.travelagent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travelagent.entity.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long>{

}
