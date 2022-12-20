package org.deblock.exercise.domain.flight;

import org.deblock.exercise.domain.flight.Flight;
import org.deblock.exercise.domain.flight.search.request.SearchFlightRequest;

import java.util.Optional;

public interface FlightSupplier {
    Optional<Flight> findBy(SearchFlightRequest flightRequest);
}
