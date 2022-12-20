package org.deblock.exercise.domain;

import java.util.Set;

public interface FlightSearch {
    Set<Flight> findAllBy(SearchFlightRequest request);
}
