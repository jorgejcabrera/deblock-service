package org.deblock.exercise.domain.flight.search.request.exception;

public class InvalidAirportCodeException extends RuntimeException {

    public InvalidAirportCodeException(String message) {
        super(message);
    }
}
