package org.deblock.exercise.domain.flight.search.request.exception;

public class InvalidAmountOfPassengersException extends RuntimeException {

    public InvalidAmountOfPassengersException(String message) {
        super(message);
    }
}
