package org.deblock.exercise.delivery.handler;

import org.deblock.exercise.delivery.handler.ErrorResponse;
import org.deblock.exercise.domain.flight.search.request.exception.InvalidAirportCodeException;
import org.deblock.exercise.domain.flight.search.request.exception.InvalidAmountOfPassengersException;
import org.deblock.exercise.domain.flight.search.request.exception.InvalidDateException;
import org.deblock.exercise.domain.flight.search.request.exception.InvalidFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidAirportCodeException.class)
    ResponseEntity<ErrorResponse> handle(InvalidAirportCodeException exception) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse("invalid_airport_code", exception.getMessage()));
    }

    @ExceptionHandler(InvalidAmountOfPassengersException.class)
    ResponseEntity<ErrorResponse> handle(InvalidAmountOfPassengersException exception) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse("invalid_amount_of_passengers", exception.getMessage()));
    }

    @ExceptionHandler(InvalidDateException.class)
    ResponseEntity<ErrorResponse> handle(InvalidDateException exception) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse("invalid_date", exception.getMessage()));
    }

    @ExceptionHandler(InvalidFieldsException.class)
    ResponseEntity<ErrorResponse> handle(InvalidFieldsException exception) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse("invalid_fields", exception.getMessage()));
    }
}
