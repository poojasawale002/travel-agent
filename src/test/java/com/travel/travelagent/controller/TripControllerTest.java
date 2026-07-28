package com.travel.travelagent.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.travel.travelagent.dto.TripResponse;
import com.travel.travelagent.service.TripService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.travel.travelagent.jwt.JwtAuthenticationFilter;
import com.travel.travelagent.jwt.JwtService;
import com.travel.travelagent.security.CustomUserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TripController.class)
@AutoConfigureMockMvc(addFilters = false)
class TripControllerTest {
	
	@MockBean
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@MockBean
	private JwtService jwtService;

	@MockBean
	private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;
    
    @Test
    void testGetTripById() throws Exception {

    	TripResponse response = new TripResponse();
    	response.setId(9L);
    	response.setSource("Mumbai");
    	response.setDestination("Goa");
    	response.setBudget(15000.0);
    	response.setDays(4);

    	when(tripService.getTripById(9L)).thenReturn(response);

    	mockMvc.perform(get("/trip/9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(9))
                .andExpect(jsonPath("$.data.source").value("Mumbai"))
                .andExpect(jsonPath("$.data.destination").value("Goa"))
                .andExpect(jsonPath("$.message").value("Trip fetched successfully"))
                .andExpect(jsonPath("$.success").value(true));
    }

}