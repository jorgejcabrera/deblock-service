package org.deblock.exercise.domain;

import java.util.Optional;

public interface FlightSupplier {
    Optional<Flight> findBy(SearchFlightRequest flightRequest);
}
