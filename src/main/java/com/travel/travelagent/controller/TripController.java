package com.travel.travelagent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travelagent.dto.TripRequest;
import com.travel.travelagent.entity.Trip;
import com.travel.travelagent.repository.TripRepository;
import com.travel.travelagent.service.TripService;

import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trip")
public class TripController {
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private TripRepository tripRepository;
	

	
//	@GetMapping
//    public List<Trip> getAllTrips() {
//        return tripService.getAllTrips();
//    }
	@GetMapping("/page")
	public Page<Trip> getTrips(Pageable pageable) {
		return tripService.getAllTrips(pageable);
		
	}
	
	// sort trips in ascending
	@GetMapping("/sort")
	public List<Trip> sortTrips() {
	    return tripRepository.findAll(Sort.by("budget"));
	}
	
	//sort trips in descending
	@GetMapping("/sort/desc")
	public List<Trip> sortTripsDesc() {
	    return tripRepository.findAll(
	            Sort.by(Sort.Direction.DESC, "budget"));
	}
	
	//sorting based on field
	@GetMapping("/sort/field")
	public List<Trip> sortTrips(
	        @RequestParam String field) {

	    return tripRepository.findAll(Sort.by(field));
	}
	
	// pagination+sorting
	@GetMapping("/page-sort")
	public Page<Trip> pageSort(Pageable pageable) {
	    return tripRepository.findAll(pageable);
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
	
	// updating all values
	@PutMapping("/{id}")
	public ResponseEntity<Trip> updateTrip(@PathVariable Long id,@Valid @RequestBody TripRequest request){
		Trip updatedTrip = tripService.updateTrip(id, request);
		
		return ResponseEntity.ok(updatedTrip);
	}
	
	//updating only budget
	@PatchMapping("/{id}/budget")
	public ResponseEntity<Trip> updateBudget(
	        @PathVariable Long id,
	        @Valid @RequestParam Double budget) {

	    Trip trip = tripService.updateBudget(id, budget);

	    return ResponseEntity.ok(trip);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteTripById(@PathVariable Long id) {
		tripService.deleteTripById(id);
		
		return "Trip deleted";
	}
	
	@PostMapping("/destinations")
	public Trip saveTrips(@RequestBody Trip trip){

	    return tripService.saveTrips(trip);

	}
	
	@GetMapping("/jpql/budget")
	public List<Trip> getBudget(@RequestParam Double budget){
	    return tripService.getTripsGreaterThan(budget);
	}

	@GetMapping("/jpql/source")
	public List<Trip> getSource(@RequestParam String source){
	    return tripService.getTripsBySourceJPQL(source);
	}

	@GetMapping("/jpql/destination")
	public List<Trip> searchDestination(@RequestParam String destination){
	    return tripService.searchDestination(destination);
	}	
}
