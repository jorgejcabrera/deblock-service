package org.deblock.exercise.infrastructure.crazyair;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.deblock.exercise.domain.flight.Flight;
import org.deblock.exercise.domain.flight.search.request.SearchFlightRequest;
import org.deblock.exercise.infrastructure.MockServer;
import org.deblock.exercise.infrastructure.NetworkConnectionConfig;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

class CrazyAirTest {
    private CrazyAir crazyAir;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        MockServer.init();
        NetworkConnectionConfig config = new NetworkConnectionConfig(ofMillis(1000),
                ofMillis(1000),
                ofMillis(1000),
                getBaseUrl());
        crazyAir = new CrazyAir(config, objectMapper);
    }

    @Test
    void testWhenFindAnExistingFlightInCrazyAirThenItMustBeRetrieved() {
        // GIVEN
        MockServer.crazyAirServer();
        SearchFlightRequest request = givenARequest();

        // WHEN
        Optional<Flight> result = crazyAir.findBy(request);

        // THEN
        thenAFlightWasFound(result);
    }

    @Test
    void testWhenThereIsNotAnyAvailableFlightThenNothingMustBeRetrieved() {
        // GIVEN
        MockServer.emptyCrazyAirServer();
        SearchFlightRequest request = givenARequest();

        // WHEN
        Optional<Flight> result = crazyAir.findBy(request);

        // THEN
        thenNothingWasFound(result);
    }

    @Test
    void testWhenCrazyAirServerIsWorkingBadlyThenAnExceptionMustBeThrown() {
        // GIVEN
        MockServer.brokenCrazyAirServer();
        SearchFlightRequest request = givenARequest();

        // THEN
        assertThrows(RuntimeException.class, () -> {
            crazyAir.findBy(request);
        });
    }

    private static void thenNothingWasFound(Optional<Flight> result) {
        assertTrue(result.isEmpty());
    }

    private static void thenAFlightWasFound(Optional<Flight> result) {
        assertNotNull(result.get());
    }

    @NotNull
    private static SearchFlightRequest givenARequest() {
        return new SearchFlightRequest.Builder()
                .withOrigin("eze")
                .withDestination("ist")
                .withDepartureDate(LocalDateTime.now().toString())
                .withReturnDate(LocalDateTime.now().plusDays(30).toString())
                .withNumberOfPassengers(Short.valueOf("1"))
                .build();
    }

    @NotNull
    private static String getBaseUrl() {
        int port = MockServer.port();
        return "http://localhost:" + port;
    }

    @AfterEach
    void finish() {
        MockServer.resetRequest();
    }

}