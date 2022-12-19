package org.deblock.exercise.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Flight {
    private String airline;

    private String supplier;

    private BigDecimal fare;

    private String departureAirportCode;

    private String destinationAirportCode;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    public Flight(String airline,
                  String supplier,
                  BigDecimal fare,
                  String departureAirportCode,
                  String destinationAirportCode,
                  LocalDateTime departureDate,
                  LocalDateTime arrivalDate) {
        this.airline = airline;
        this.supplier = supplier;
        this.fare = fare;
        this.departureAirportCode = departureAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
}
