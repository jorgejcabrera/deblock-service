package org.deblock.exercise.domain;

import org.deblock.exercise.domain.flight.search.request.SearchFlightRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SearchFlightRequestTest {

    @Test
    void testWhenAtLeastOneFieldIsEmptyThenAnExceptionMustBeThrown() {
        // WHEN
        Exception ex = assertThrows(RuntimeException.class, () -> new SearchFlightRequest.Builder()
                .withReturnDate(LocalDateTime.now().plusDays(10).toString())
                .withDestination("ist")
                .withOrigin("eze")
                .withNumberOfPassengers(Short.valueOf("2"))
                .build()
        );

        // THEN
        assertEquals("Origin, destination, departureDate, returnDate and numberOfPassengers are mandatory", ex.getMessage());
    }

    @Test
    void testWhenTheAmountOfPassengersIsInvalidThenAnExceptionMustBeThrown() {
        // WHEN
        Exception ex = assertThrows(RuntimeException.class, () -> new SearchFlightRequest.Builder()
                .withDepartureDate(LocalDateTime.now().toString())
                .withReturnDate(LocalDateTime.now().plusDays(10).toString())
                .withDestination("ist")
                .withOrigin("eze")
                .withNumberOfPassengers(Short.valueOf("6"))
                .build()
        );

        // THEN
        assertEquals("Maximum number of passenger are 4", ex.getMessage());
    }

    @Test
    void testWhenTheOriginCodeIsWrongThenAnExceptionMustBeThrown() {
        // WHEN
        Exception ex = assertThrows(RuntimeException.class, () -> new SearchFlightRequest.Builder()
                .withDepartureDate(LocalDateTime.now().toString())
                .withReturnDate(LocalDateTime.now().plusDays(10).toString())
                .withDestination("ist")
                .withOrigin("ezasde")
                .withNumberOfPassengers(Short.valueOf("2"))
                .build()
        );

        // THEN
        assertEquals("Invalid origin airport code", ex.getMessage());
    }

    @Test
    void testWhenTheDestinationCodeIsWrongThenAnExceptionMustBeThrown() {
        // WHEN
        Exception ex = assertThrows(RuntimeException.class, () -> new SearchFlightRequest.Builder()
                .withDepartureDate(LocalDateTime.now().toString())
                .withReturnDate(LocalDateTime.now().plusDays(10).toString())
                .withDestination("asdist")
                .withOrigin("eze")
                .withNumberOfPassengers(Short.valueOf("2"))
                .build()
        );

        // THEN
        assertEquals("Invalid destination airport code", ex.getMessage());
    }

    @Test
    void testWhenTheDatesAreInvalidThenAnExceptionMustBeThrown() {
        // WHEN
        Exception ex = assertThrows(RuntimeException.class, () -> new SearchFlightRequest.Builder()
                .withReturnDate(LocalDateTime.now().toString())
                .withDepartureDate(LocalDateTime.now().plusDays(10).toString())
                .withDestination("ist")
                .withOrigin("eze")
                .withNumberOfPassengers(Short.valueOf("2"))
                .build()
        );

        // THEN
        assertEquals("The returned date must be greater than the departure date", ex.getMessage());
    }

}