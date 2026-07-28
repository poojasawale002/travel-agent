	package com.travel.travelagent.service;
	
import java.util.List;


import org.modelmapper.ModelMapper;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	
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
		
		
	//	public List<Trip> getAllTrips() {
	//        return tripRepository.findAll();
	//    }
	//	
		
		private TripResponse convertToResponse(Trip trip) {

		    return modelMapper.map(trip, TripResponse.class);

		}
		
		public Page<TripResponse> getAllTrips(Pageable pageable) {

		    return tripRepository
		            .findAll(pageable)
		            .map(this::convertToResponse);
		}
		
		public TripResponse getTripById(Long id) {

		    Trip trip = tripRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Trip not found"));

		    return convertToResponse(trip);
		}
	
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
	
		public List<TripResponse> getTripsBySourceAndDestination(String source,
                String destination) {
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
		
		public TripResponse saveTrip(TripRequest request) {

		    Trip trip = new Trip();

		    trip.setSource(request.getSource());
		    trip.setDestination(request.getDestination());
		    trip.setBudget(request.getBudget());
		    trip.setDays(request.getDays());

		    Trip savedTrip = tripRepository.save(trip);

		    return convertToResponse(savedTrip);
		}
		// updating all fields
		public TripResponse updateTrip(Long id, TripRequest request) {

		    Trip trip = tripRepository.findById(id)
		            .orElseThrow(() -> new TripNotFoundException("Trip not found"));

		    trip.setSource(request.getSource());
		    trip.setDestination(request.getDestination());
		    trip.setDays(request.getDays());
		    trip.setBudget(request.getBudget());

		    Trip updatedTrip = tripRepository.save(trip);

		    return convertToResponse(updatedTrip);
		}
		
		// updating only budget
		public TripResponse updateBudget(Long id, Double budget) {

		    Trip trip = tripRepository.findById(id)
		            .orElseThrow(() -> new TripNotFoundException("Trip not found"));

		    trip.setBudget(budget);

		    Trip updatedTrip = tripRepository.save(trip);

		    return convertToResponse(updatedTrip);
		}
		
		
		public void deleteTripById(Long id) {
	
	
			Trip trip = tripRepository.findById(id).orElseThrow(() -> new TripNotFoundException("Trip not found"));
			
			tripRepository.delete(trip);
		}
	
		public TripResponse saveTrips(Trip trip) {

		    Long userId = trip.getUser().getId();

		    User user = userRepository.findById(userId)
		            .orElseThrow(() -> new UserNotFoundException("User not found"));

		    trip.setUser(user);

		    Trip savedTrip = tripRepository.save(trip);

		    return convertToResponse(savedTrip);
		}
		
		public List<TripResponse> getTripsGreaterThan(Double budget){

		    return tripRepository.findTripsGreaterThanBudget(budget)
		            .stream()
		            .map(this::convertToResponse)
		            .toList();
		}

		public List<TripResponse> getTripsBySourceJPQL(String source){

		    return tripRepository.findTripsBySource(source)
		            .stream()
		            .map(this::convertToResponse)
		            .toList();
		}

		public List<TripResponse> searchDestination(String destination){

		    return tripRepository.searchDestination(destination)
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

		    return tripRepository.findAll(
		            Sort.by(Sort.Direction.DESC, "budget"))
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
	}
