package com.travel.travelagent.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.travel.travelagent.dto.TripRequest;
import com.travel.travelagent.dto.TripResponse;
import com.travel.travelagent.entity.Trip;
import com.travel.travelagent.entity.User;
import com.travel.travelagent.exception.TripNotFoundException;
import com.travel.travelagent.exception.UserNotFoundException;
import com.travel.travelagent.repository.TripRepository;
import com.travel.travelagent.repository.UserRepository;

import io.micrometer.core.annotation.Timed;

@Service
public class TripService {

    private static final Logger logger =
            LoggerFactory.getLogger(TripService.class);

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MetricService metricService;

    private TripResponse convertToResponse(Trip trip) {
        return modelMapper.map(trip, TripResponse.class);
    }

    // ==================== GET ALL ====================

    public Page<TripResponse> getAllTrips(Pageable pageable) {

        logger.info("Fetching all trips");

        return tripRepository.findAll(pageable)
                .map(this::convertToResponse);
    }

    // ==================== GET BY ID ====================

    public TripResponse getTripById(Long id) {

        logger.info("Fetching trip with id {}", id);

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Trip not found {}", id);
                    return new TripNotFoundException("Trip not found");
                });

        logger.info("Trip fetched successfully");

        return convertToResponse(trip);
    }

    // ==================== CREATE ====================

    @Timed(
            value = "trip.create.time",
            description = "Time taken to create trip")
    public TripResponse saveTrip(TripRequest request) {

        logger.info("Creating new trip");

        Trip trip = new Trip();

        trip.setSource(request.getSource());
        trip.setDestination(request.getDestination());
        trip.setBudget(request.getBudget());
        trip.setDays(request.getDays());

        Trip savedTrip = tripRepository.save(trip);

        metricService.incrementTripCounter();

        logger.info("Trip created successfully with id {}", savedTrip.getId());

        return convertToResponse(savedTrip);
    }

    // ==================== UPDATE ====================

    public TripResponse updateTrip(Long id, TripRequest request) {

        logger.info("Updating trip {}", id);

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException("Trip not found"));

        trip.setSource(request.getSource());
        trip.setDestination(request.getDestination());
        trip.setBudget(request.getBudget());
        trip.setDays(request.getDays());

        Trip updatedTrip = tripRepository.save(trip);

        logger.info("Trip updated successfully");

        return convertToResponse(updatedTrip);
    }

    // ==================== UPDATE BUDGET ====================

    public TripResponse updateBudget(Long id, Double budget) {

        logger.info("Updating budget for trip {}", id);

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException("Trip not found"));

        trip.setBudget(budget);

        Trip updatedTrip = tripRepository.save(trip);

        logger.info("Budget updated successfully");

        return convertToResponse(updatedTrip);
    }

    // ==================== DELETE ====================

    public void deleteTripById(Long id) {

        logger.info("Deleting trip {}", id);

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException("Trip not found"));

        tripRepository.delete(trip);

        logger.info("Trip deleted successfully");
    }

    // ==================== SAVE USER TRIP ====================

    public TripResponse saveTrips(Trip trip) {

        Long userId = trip.getUser().getId();

        logger.info("Saving trip for user {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        trip.setUser(user);

        Trip savedTrip = tripRepository.save(trip);

        logger.info("Trip saved successfully");

        return convertToResponse(savedTrip);
    }

    // ==================== DERIVED QUERIES ====================

    public List<TripResponse> getTripBySource(String source) {
        return tripRepository.findBySource(source)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> getTripByDestination(String destination) {
        return tripRepository.findByDestination(destination)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> getTripByBudget(Double budget) {
        return tripRepository.findByBudgetLessThan(budget)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> getTripsByBudgetGreaterThan(Double budget) {
        return tripRepository.findByBudgetGreaterThan(budget)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> getTripsBySourceAndDestination(String source, String destination) {
        return tripRepository.findBySourceAndDestination(source, destination)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> getTripsBySourceOrDestination(String source, String destination) {
        return tripRepository.findBySourceOrDestination(source, destination)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> getTripsStartingWith(String destination) {
        return tripRepository.findByDestinationStartingWith(destination)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> getTripsEndingWith(String destination) {
        return tripRepository.findByDestinationEndingWith(destination)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // ==================== SORTING ====================

    public List<TripResponse> getTripsSortedByBudgetAsc() {
        return tripRepository.findByOrderByBudgetAsc()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> getTripsSortedByBudgetDesc() {
        return tripRepository.findByOrderByBudgetDesc()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> sortTrips() {
        return tripRepository.findAll(Sort.by("budget"))
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> sortTripsDesc() {
        return tripRepository.findAll(Sort.by(Sort.Direction.DESC, "budget"))
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> sortTrips(String field) {
        return tripRepository.findAll(Sort.by(field))
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public Page<TripResponse> pageSort(Pageable pageable) {
        return tripRepository.findAll(pageable)
                .map(this::convertToResponse);
    }

    // ==================== JPQL ====================

    public List<TripResponse> getTripsGreaterThan(Double budget) {

        logger.info("Fetching trips with budget greater than {}", budget);

        return tripRepository.findTripsGreaterThanBudget(budget)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> getTripsBySourceJPQL(String source) {

        logger.info("Searching source {}", source);

        return tripRepository.findTripsBySource(source)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<TripResponse> searchDestination(String destination) {

        logger.info("Searching destination {}", destination);

        return tripRepository.searchDestination(destination)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }
}