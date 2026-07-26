package com.travel.travelagent.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="trip_user")
public class User {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String name;

	    @Column(unique = true, nullable = false)
	    private String email;
	    
	    private String password;
	    
	    @JsonManagedReference
	    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	    private List<Trip> trips;
	    
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name="user_roles",
	        joinColumns=@JoinColumn(name="user_id"),
	        inverseJoinColumns=@JoinColumn(name="role_id")
	    )
	    private List<Role> roles;


	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<Trip> getTrips() {
			return trips;
		}

		public void setTrips(List<Trip> trips) {
			this.trips = trips;
		}
		
		public List<Role> getRoles() {
		    return roles;
		}

		public void setRoles(List<Role> roles) {
		    this.roles = roles;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
}
