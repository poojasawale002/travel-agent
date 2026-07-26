package com.travel.travelagent.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name= "trip")
public class Trip {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @NotBlank(message="Source cannnot be empty")
	 private String source;

	 @NotBlank(message="Destination cannnot be empty")
	 private String destination;

	 @NotNull(message = "Days are required")
	 @Positive(message="Days must be greater than zero")
	 private Integer days;

	 @NotNull(message = "Budget is required")
	 @Positive(message="Budget must be greater than zero")
	 private Double budget;
	 
	 @JsonBackReference
	 @ManyToOne
	 @JoinColumn(name = "user_id")
	 private User user;
	 
	 @ManyToMany(cascade = CascadeType.ALL)
	 @JoinTable(
	         name="trip_destination",
	         joinColumns=@JoinColumn(name="trip_id"),
	         inverseJoinColumns=@JoinColumn(name="destination_id")
	 )
	 private List<Destination> destinations;
	 
	 public Trip() {
		 
	 }

	  public Trip(Long id, String source, String destination, Double budget) {
	        this.id = id;
	        this.source = source;
	        this.destination = destination;
	        this.budget = budget;
	    }
	  
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}
	
	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}

}
