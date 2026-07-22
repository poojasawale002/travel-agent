package com.travel.travelagent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travelagent.dto.TripRequest;
import com.travel.travelagent.entity.Trip;
import com.travel.travelagent.service.TripService;

import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trip")
public class TripController {
	
	@Autowired
	private TripService tripService;
	
	@GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Trip createTrip(@Valid @RequestBody TripRequest request) {
		
		Trip trip = new Trip();

	    trip.setSource(request.getSource());
	    trip.setDestination(request.getDestination());
	    trip.setDays(request.getDays());
	    trip.setBudget(request.getBudget());

	    return tripService.saveTrip(trip);
		
		
	}
	
	@GetMapping("/{id}")
	public Trip getTripById(@PathVariable Long id) {
		return tripService.getTripById(id);
		
	}

	@GetMapping("/source")
	public List<Trip> getTripBySource(@RequestParam String source) {
	    return tripService.getTripBySource(source);
	}
	
	@GetMapping("/destination")
	public List<Trip> getTripsByDestination(@RequestParam String destination) {
	    return tripService.getTripByDestination(destination);
	}
	
	@GetMapping("/budget")
	public List<Trip> getTripsByBudget(@RequestParam Double budget) {
	    return tripService.getTripByBudget(budget);
	}
	
	@GetMapping("/budget/greater")
	public List<Trip> getTripsByBudgetGreaterThan(@RequestParam Double budget) {
	    return tripService.getTripsByBudgetGreaterThan(budget);
	}
	
	@GetMapping("/search")
	public List<Trip> getTripsBySourceAndDestination(
	        @RequestParam String source,
	        @RequestParam String destination) {

	    return tripService.getTripsBySourceAndDestination(source, destination);
	}
	
	
	@GetMapping("/search/or")
	public List<Trip> getTripsBySourceOrDestination(
	        @RequestParam String source,
	        @RequestParam String destination) {

	    return tripService.getTripsBySourceOrDestination(source, destination);
	}
	
	@GetMapping("/destination/start")
	public List<Trip> getTripsStartingWith(@RequestParam String destination) {
	    return tripService.getTripsStartingWith(destination);
	}
	
	@GetMapping("/destination/end")
	public List<Trip> getTripsEndingWith(@RequestParam String destination) {
	    return tripService.getTripsEndingWith(destination);
	}
	
	@GetMapping("/budget/asc")
	public List<Trip> getTripsSortedByBudgetAsc() {
	    return tripService.getTripsSortedByBudgetAsc();
	}
	
	@GetMapping("/budget/desc")
	public List<Trip> getTripsSortedByBudgetDesc() {
	    return tripService.getTripsSortedByBudgetDesc();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteTripById(@PathVariable Long id) {
		tripService.deleteTripById(id);
		
		return "Trip deleted";
	}
}
