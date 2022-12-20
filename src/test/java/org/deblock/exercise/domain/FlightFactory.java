package org.deblock.exercise.domain;

import org.deblock.exercise.domain.flight.Flight;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlightFactory {

    static Flight fromEzeToIst() {
        return new Flight(
                "airline",
                "supplier",
                new BigDecimal(9),
                "EZE",
                "IST",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30));
    }

    static Flight cheapestFromEzeToIst() {
        return new Flight(
                "airline",
                "supplier",
                new BigDecimal(1),
                "EZE",
                "IST",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30));
    }
}
