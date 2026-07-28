package com.travel.travelagent.dto;

public class TripResponse {

    private Long id;
    private String source;
    private String destination;
    private Integer days;
    private Double budget;

    public TripResponse() {
    }

    public TripResponse(Long id, String source, String destination,
                        Integer days, Double budget) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.days = days;
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
}