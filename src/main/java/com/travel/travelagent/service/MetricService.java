package com.travel.travelagent.service;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class MetricService {

    private final Counter tripCounter;

    public MetricService(MeterRegistry registry) {

        tripCounter = Counter.builder("trip.created")
                .description("Total trips created")
                .register(registry);
    }

    public void incrementTripCounter() {
        tripCounter.increment();
    }
}