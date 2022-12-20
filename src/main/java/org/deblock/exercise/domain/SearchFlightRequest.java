package org.deblock.exercise.domain;

import java.time.LocalDateTime;

public class SearchFlightRequest {
    private String origin;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private Short numberOfPassengers;

    private SearchFlightRequest(String origin,
                                String destination,
                                LocalDateTime departureDate,
                                LocalDateTime returnDate,
                                Short numberOfPassengers) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public Short getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public static class Builder {
        private String origin;
        private String destination;
        private LocalDateTime departureDate;
        private LocalDateTime returnDate;
        private Short numberOfPassengers;

        public Builder() {
        }

        public Builder withOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public Builder withDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder withDepartureDate(LocalDateTime departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public Builder withReturnDate(LocalDateTime returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder withNumberOfPassengers(Short numberOfPassengers) {
            this.numberOfPassengers = numberOfPassengers;
            return this;
        }

        private void validate() {
            if (!allFieldsWereInitialized()) {
                throw new RuntimeException("Origin, destination, departureDate, returnDate and numberOfPassengers are mandatory");
            }
            if (datesAreInvalid(this.departureDate, this.returnDate)) {
                throw new RuntimeException("The returned must be greater than the departure date");
            }
            if (!isAValidAirportIATACode(origin)) {
                throw new RuntimeException("Invalid origin airport code");
            }
            if (!isAValidAirportIATACode(destination)) {
                throw new RuntimeException("Invalid destination airport code");
            }
            if (maximumNumberOfPassengerHasBeenReached()) {
                throw new RuntimeException("Maximum number of passenger are 4");
            }
        }

        private boolean datesAreInvalid(LocalDateTime departureDate, LocalDateTime returnDate) {
            return departureDate.isAfter(returnDate);
        }

        private boolean maximumNumberOfPassengerHasBeenReached() {
            return numberOfPassengers > 4;
        }

        private boolean allFieldsWereInitialized() {
            return origin != null && destination != null && departureDate != null && returnDate != null && numberOfPassengers != null;
        }

        private boolean isAValidAirportIATACode(String airportCode) {
            return airportCode.length() == 3;
        }

        public SearchFlightRequest build() {
            this.validate();
            return new SearchFlightRequest(this.origin,
                    this.destination,
                    this.departureDate,
                    this.returnDate,
                    this.numberOfPassengers);
        }
    }
}
