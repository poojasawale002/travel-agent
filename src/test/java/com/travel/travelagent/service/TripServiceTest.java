package com.travel.travelagent.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.travel.travelagent.dto.TripResponse;
import com.travel.travelagent.entity.Trip;
import com.travel.travelagent.repository.TripRepository;
import com.travel.travelagent.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TripService tripService;
    
    @Test
    void testGetTripById() {

        // Arrange
        Trip trip = new Trip();
        trip.setId(1L);
        trip.setSource("Mumbai");
        trip.setDestination("Goa");
        trip.setBudget(15000.0);
        trip.setDays(4);

        TripResponse response = new TripResponse();
        response.setId(1L);
        response.setSource("Mumbai");
        response.setDestination("Goa");
        response.setBudget(15000.0);
        response.setDays(4);

        when(tripRepository.findById(1L))
                .thenReturn(Optional.of(trip));

        when(modelMapper.map(trip, TripResponse.class))
                .thenReturn(response);

        // Act
        TripResponse result = tripService.getTripById(1L);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Mumbai", result.getSource());
        assertEquals("Goa", result.getDestination());
    }

}