package org.deblock.exercise.domain.flight;

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

    public String getAirline() {
        return airline;
    }

    public String getSupplier() {
        return supplier;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }
}
