package com.travel.travelagent.dto;

import jakarta.validation.constraints.NotBlank;

public class TripRequest {
	
	@NotBlank(message="Source cannnot be empty")
	private String source;
	
	@NotBlank(message="Destination cannnot be empty")
    private String destination;
    private int days;
    private double budget;
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
