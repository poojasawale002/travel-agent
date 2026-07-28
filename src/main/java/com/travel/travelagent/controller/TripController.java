package com.travel.travelagent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.travelagent.dto.ApiResponse;
import com.travel.travelagent.dto.TripRequest;
import com.travel.travelagent.dto.TripResponse;
import com.travel.travelagent.entity.Trip;
import com.travel.travelagent.service.TripService;
import com.travel.travelagent.util.ResponseBuilder;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
	    name = "Trip APIs",
	    description = "Operations related to Trips"
	)
	@RestController
	@RequestMapping("/trip")
	public class TripController {

    @Autowired
    private TripService tripService;

    // ================= CREATE =================

    @PostMapping
    public ResponseEntity<ApiResponse<TripResponse>> createTrip(
            @Valid @RequestBody TripRequest request) {

        return ResponseBuilder.created(
                "Trip created successfully",
                tripService.saveTrip(request)
        );
    }

    // ================= GET ALL =================

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<TripResponse>>> getTrips(Pageable pageable) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getAllTrips(pageable)
        );
    }

    // ================= GET BY ID =================

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TripResponse>> getTripById(
            @PathVariable Long id) {

        return ResponseBuilder.success(
                "Trip fetched successfully",
                tripService.getTripById(id)
        );
    }

    // ================= SEARCH =================

    @GetMapping("/source")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripBySource(
            @RequestParam String source) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripBySource(source)
        );
    }

    @GetMapping("/destination")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripByDestination(
            @RequestParam String destination) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripByDestination(destination)
        );
    }

    @GetMapping("/budget")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripByBudget(
            @RequestParam Double budget) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripByBudget(budget)
        );
    }

    @GetMapping("/budget/greater")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripByBudgetGreaterThan(
            @RequestParam Double budget) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripsByBudgetGreaterThan(budget)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripsBySourceAndDestination(
            @RequestParam String source,
            @RequestParam String destination) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripsBySourceAndDestination(source, destination)
        );
    }

    @GetMapping("/search/or")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripsBySourceOrDestination(
            @RequestParam String source,
            @RequestParam String destination) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripsBySourceOrDestination(source, destination)
        );
    }

    @GetMapping("/destination/start")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripsStartingWith(
            @RequestParam String destination) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripsStartingWith(destination)
        );
    }

    @GetMapping("/destination/end")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripsEndingWith(
            @RequestParam String destination) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripsEndingWith(destination)
        );
    }

    // ================= SORT =================

    @GetMapping("/sort")
    public ResponseEntity<ApiResponse<List<TripResponse>>> sortTrips() {

        return ResponseBuilder.success(
                "Trips sorted successfully",
                tripService.sortTrips()
        );
    }

    @GetMapping("/sort/desc")
    public ResponseEntity<ApiResponse<List<TripResponse>>> sortTripsDesc() {

        return ResponseBuilder.success(
                "Trips sorted successfully",
                tripService.sortTripsDesc()
        );
    }

    @GetMapping("/sort/field")
    public ResponseEntity<ApiResponse<List<TripResponse>>> sortTrips(
            @RequestParam String field) {

        return ResponseBuilder.success(
                "Trips sorted successfully",
                tripService.sortTrips(field)
        );
    }

    @GetMapping("/page-sort")
    public ResponseEntity<ApiResponse<Page<TripResponse>>> pageSort(
            Pageable pageable) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.pageSort(pageable)
        );
    }

    // ================= UPDATE =================

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TripResponse>> updateTrip(
            @PathVariable Long id,
            @Valid @RequestBody TripRequest request) {

        return ResponseBuilder.success(
                "Trip updated successfully",
                tripService.updateTrip(id, request)
        );
    }

    @PatchMapping("/{id}/budget")
    public ResponseEntity<ApiResponse<TripResponse>> updateBudget(
            @PathVariable Long id,
            @RequestParam Double budget) {

        return ResponseBuilder.success(
                "Budget updated successfully",
                tripService.updateBudget(id, budget)
        );
    }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTrip(
            @PathVariable Long id) {

        tripService.deleteTripById(id);

        return ResponseBuilder.success(
                "Trip deleted successfully",
                "Deleted"
        );
    }

    // ================= USER TRIPS =================

    @PostMapping("/destinations")
    public ResponseEntity<ApiResponse<TripResponse>> saveTripForUser(
            @RequestBody Trip trip) {

        return ResponseBuilder.created(
                "Trip added successfully",
                tripService.saveTrips(trip)
        );
    }

    // ================= JPQL =================

    @GetMapping("/jpql/budget")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getBudget(
            @RequestParam Double budget) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripsGreaterThan(budget)
        );
    }

    @GetMapping("/jpql/source")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getSource(
            @RequestParam String source) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.getTripsBySourceJPQL(source)
        );
    }

    @GetMapping("/jpql/destination")
    public ResponseEntity<ApiResponse<List<TripResponse>>> searchDestination(
            @RequestParam String destination) {

        return ResponseBuilder.success(
                "Trips fetched successfully",
                tripService.searchDestination(destination)
        );
    }
}