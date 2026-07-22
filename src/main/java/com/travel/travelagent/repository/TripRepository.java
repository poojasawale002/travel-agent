package com.travel.travelagent.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travelagent.entity.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{

	List<Trip> findBySource(String source);	
	
	List<Trip> findByDestination(String destination);

    List<Trip> findByBudgetLessThan(Double budget);
    
    List<Trip> findByBudgetGreaterThan(Double budget);
    
    List<Trip> findBySourceAndDestination(String source, String destination);
    
    List<Trip> findBySourceOrDestination(String source, String destination);
    
    List<Trip> findByDestinationStartingWith(String destination);
    
    List<Trip> findByDestinationEndingWith(String destination);
    
    List<Trip> findByOrderByBudgetAsc();

	List<Trip> findByOrderByBudgetDesc();
    
}
