package org.deblock.exercise.delivery.rest;

import org.deblock.exercise.domain.flight.Flight;
import org.deblock.exercise.domain.flight.search.FlightSearch;
import org.deblock.exercise.domain.flight.search.request.SearchFlightRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class SearchFlightController {
    @Autowired
    private FlightSearch flightSearch;

    @GetMapping(value = "/v1/flights")
    ResponseEntity<Set<Flight>> rent(
            @Nullable @RequestParam("origin") String origin,
            @Nullable @RequestParam("destination") String destination,
            @Nullable @RequestParam("departureDate") String departureDate,
            @Nullable @RequestParam("returnDate") String returnDate,
            @Nullable @RequestParam("numberOfPassengers") Short numberOfPassengers
    ) {
        SearchFlightRequest request = new SearchFlightRequest.Builder()
                .withOrigin(origin)
                .withDestination(destination)
                .withNumberOfPassengers(numberOfPassengers)
                .withDepartureDate(departureDate)
                .withReturnDate(returnDate)
                .build();
        Set<Flight> flights = flightSearch.findAllBy(request);
        return ResponseEntity.ok(flights);
    }
}
