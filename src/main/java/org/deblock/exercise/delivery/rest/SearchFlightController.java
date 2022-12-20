package org.deblock.exercise.delivery.rest;

import org.deblock.exercise.domain.Flight;
import org.deblock.exercise.domain.FlightSearch;
import org.deblock.exercise.domain.SearchFlightRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Set;

@Controller
public class SearchFlightController {
    @Autowired
    private FlightSearch flightSearch;

    @GetMapping(value = "/v1/flight")
    ResponseEntity<Set<Flight>> rent(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination,
            @RequestParam("departureDate") LocalDateTime departureDate,
            @RequestParam("returndDate") LocalDateTime returnDate,
            @RequestParam("numberOfPassengers") Short numberOfPassengers
    ) {
        SearchFlightRequest request = new SearchFlightRequest.Builder()
                .withOrigin(origin)
                .withDestination(destination)
                .withNumberOfPassengers(numberOfPassengers)
                .withReturnDate(departureDate)
                .withReturnDate(returnDate)
                .build();
        Set<Flight> flights = flightSearch.findAllBy(request);
        return ResponseEntity.ok(flights);
    }
}
