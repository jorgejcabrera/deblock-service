package org.deblock.exercise.domain;

import org.deblock.exercise.delivery.SearchFlightRequest;

import java.util.Optional;

public interface FlightSupplier {
    Optional<Flight> findBy(SearchFlightRequest flightRequest);
}
