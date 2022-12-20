package org.deblock.exercise.infrastructure.crazyair;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.deblock.exercise.domain.flight.Flight;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CrazyAirResponse {
    @JsonProperty("name")
    private String name;
    @JsonProperty("airline")
    private String airline;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("cabinclass")
    private Character cabinClass;
    @JsonProperty("departureAirportCode")
    private String departureAirportCode;
    @JsonProperty("destinationAirportCode")
    private String destinationAirportCode;
    @JsonProperty("departureDate")
    private String departureDate;
    @JsonProperty("arrivalDate")
    private String arrivalDate;

    public CrazyAirResponse() {
    }

    Flight toModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        return new Flight(this.airline,
                "crazyair",
                this.price,
                this.departureAirportCode,
                this.destinationAirportCode,
                LocalDateTime.parse(this.departureDate, formatter),
                LocalDateTime.parse(this.arrivalDate, formatter));
    }

}
