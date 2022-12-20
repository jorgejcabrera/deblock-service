package org.deblock.exercise.domain;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class DefaultFlightSearchTest {

    @Mock
    private FlightSupplier flightSupplier;

    @Mock
    private FlightSupplier anotherFlightSupplier;

    private FlightSearch flightSearch;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Set<FlightSupplier> suppliers = Set.of(flightSupplier, anotherFlightSupplier);
        flightSearch = new DefaultFlightSearch(suppliers);
    }

    @Test
    void testWhenThereIsAtLeastOneFlightThenItMustBeRetrieved() {
        // GIVEN
        SearchFlightRequest request = givenARequest();
        when(flightSupplier.findBy(any())).thenReturn(Optional.of(FlightFactory.fromEzeToIst()));

        // WHEN
        Set<Flight> results = flightSearch.findAllBy(request);

        // THEN
        thenAtLeastOneFlightMustBeRetrieved(results);
    }

    @Test
    void testWhenThereAreMoreThanOneResultForTheSameRequestThenItMustBeRetrieved() {
        // GIVEN
        SearchFlightRequest request = givenARequest();
        when(flightSupplier.findBy(any())).thenReturn(Optional.of(FlightFactory.fromEzeToIst()));
        when(anotherFlightSupplier.findBy(any())).thenReturn(Optional.of(FlightFactory.cheapestFromEzeToIst()));

        // WHEN
        Set<Flight> results = flightSearch.findAllBy(request);

        // THEN
        thenAtLeastOneFlightMustBeRetrieved(results);
        thenBothResultWereRetrieved(results);
    }

    @Test
    void testWhenTwoDifferentSuppliersRetrievedTheSameFlightThenOnlyOneResultMustBeRetrieved() {
        // GIVEN
        SearchFlightRequest request = givenARequest();
        Flight flight = FlightFactory.fromEzeToIst();
        when(flightSupplier.findBy(any())).thenReturn(Optional.of(flight));
        when(anotherFlightSupplier.findBy(any())).thenReturn(Optional.of(flight));

        // WHEN
        Set<Flight> results = flightSearch.findAllBy(request);

        // THEN
        assertEquals(1, results.size());
    }

    @Test
    void testWhenThereIsNotAtLeastOneResultThenAnEmptyListMustBeRetrieved() {
        // GIVEN
        SearchFlightRequest request = givenARequest();

        // WHEN
        Set<Flight> results = flightSearch.findAllBy(request);

        // THEN
        assertTrue(results.isEmpty());
    }

    @NotNull
    private static SearchFlightRequest givenARequest() {
        return new SearchFlightRequest.Builder()
                .withOrigin("eze")
                .withDestination("ist")
                .withDepartureDate(LocalDateTime.now())
                .withReturnDate(LocalDateTime.now().plusDays(30))
                .withNumberOfPassengers(Short.valueOf("2"))
                .build();
    }

    private void thenBothResultWereRetrieved(Set<Flight> results) {
        assertEquals(2, results.size());
    }

    private static void thenAtLeastOneFlightMustBeRetrieved(Set<Flight> results) {
        assertTrue(results.size() >= 1);
    }
}