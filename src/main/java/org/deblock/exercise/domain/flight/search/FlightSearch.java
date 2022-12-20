package org.deblock.exercise.domain.flight.search;

import org.deblock.exercise.domain.flight.Flight;
import org.deblock.exercise.domain.flight.search.request.SearchFlightRequest;

import java.util.Set;

public interface FlightSearch {
    Set<Flight> findAllBy(SearchFlightRequest request);
}
