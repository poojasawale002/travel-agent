package com.travel.travelagent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.travelagent.dto.TripRequest;
import com.travel.travelagent.entity.Trip;
import com.travel.travelagent.exception.TripNotFoundException;
import com.travel.travelagent.repository.TripRepository;

@Service
public class TripService {

	@Autowired
	private TripRepository tripRepository;
	
	
//	public Map<String , Object> planTrip(TripRequest trip) {
//		
//		Map<String, Object> response= new HashMap<>();
//		
//		response.put("Message","Trip planned successfully");
//		response.put("trip", trip);
//		
//		return response;
//	}
//	
	public String createTrip(TripRequest request) {
		
		Trip t=new Trip();
		
		t.setSource(request.getSource());
		t.setDestination(request.getDestination());
		t.setBudget(request.getBudget());
		t.setDays(request.getDays());
		
		tripRepository.save(t);
		
		return "Trip created successfully";
	}
	
	public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
	
	public Trip getTripById(Long id) {
		return tripRepository.findById(id).orElseThrow(()-> new TripNotFoundException("Trip Not Found"));
	}

	public List<Trip> getTripBySource(String source) {
		return tripRepository.findBySource(source);
	}
	
	public List<Trip> getTripByDestination(String destination) {
		return tripRepository.findByDestination(destination);
	}
	
	public List<Trip> getTripByBudget(Double budget) {
		return tripRepository.findByBudgetLessThan(budget);
	}
	
	public List<Trip> getTripsByBudgetGreaterThan(Double budget) {
	    return tripRepository.findByBudgetGreaterThan(budget);
	}

	public List<Trip> getTripsBySourceAndDestination(String source, String destination) {
	    return tripRepository.findBySourceAndDestination(source, destination);
	}
	
	public List<Trip> getTripsBySourceOrDestination(String source, String destination) {
	    return tripRepository.findBySourceOrDestination(source, destination);
	}
	
	public List<Trip> getTripsStartingWith(String destination) {
	    return tripRepository.findByDestinationStartingWith(destination);
	}
	
	public List<Trip> getTripsEndingWith(String destination) {
	    return tripRepository.findByDestinationEndingWith(destination);
	}
	
	public List<Trip> getTripsSortedByBudgetAsc() {
	    return tripRepository.findByOrderByBudgetAsc();
	}
	
	public List<Trip> getTripsSortedByBudgetDesc() {
	    return tripRepository.findByOrderByBudgetDesc();
	}
	
	public Trip saveTrip(Trip trip) {
		
		return tripRepository.save(trip);
	}

	public void deleteTripById(Long id) {


		Trip trip = tripRepository.findById(id).orElseThrow(() -> new TripNotFoundException("Trip not found"));
		
		tripRepository.delete(trip);
	}

	
}
