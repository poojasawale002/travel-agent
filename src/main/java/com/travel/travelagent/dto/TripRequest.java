package com.travel.travelagent.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TripRequest {
	
	@NotBlank(message="Source cannnot be empty")
	private String source;
	
	@NotBlank(message="Destination cannnot be empty")
    private String destination;

	 @NotNull(message = "Days are required")
	 @Positive(message = "Days must be greater than zero")
	 private Integer days;

	 @NotNull(message = "Budget is required")
	 @Positive(message = "Budget must be greater than zero")
	 private Double budget;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
    
    

}
