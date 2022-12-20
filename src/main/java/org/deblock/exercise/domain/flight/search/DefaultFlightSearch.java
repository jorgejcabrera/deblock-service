package org.deblock.exercise.domain.flight.search;

import org.deblock.exercise.domain.flight.FlightSupplier;
import org.deblock.exercise.domain.flight.Flight;
import org.deblock.exercise.domain.flight.search.request.SearchFlightRequest;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultFlightSearch implements FlightSearch {
    private final Set<FlightSupplier> suppliers;

    public DefaultFlightSearch(Set<FlightSupplier> suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public Set<Flight> findAllBy(SearchFlightRequest request) {
        Set<Flight> flights = ConcurrentHashMap.newKeySet();
        suppliers.parallelStream().forEach(supplier ->
                supplier.findBy(request).ifPresent(flights::add)
        );
        return flights;
    }
}
