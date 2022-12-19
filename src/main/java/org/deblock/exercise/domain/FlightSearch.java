package org.deblock.exercise.domain;

import org.deblock.exercise.delivery.SearchFlightRequest;

import java.util.Set;

public interface FlightSearch {
    Set<Flight> findAllBy(SearchFlightRequest request);
}
