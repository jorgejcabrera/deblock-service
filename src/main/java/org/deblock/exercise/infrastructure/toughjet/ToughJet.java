package org.deblock.exercise.infrastructure.toughjet;


import org.deblock.exercise.domain.SearchFlightRequest;
import org.deblock.exercise.domain.Flight;
import org.deblock.exercise.domain.FlightSupplier;

import java.util.Optional;

/*
* Request

Name	Description
from	3 letter IATA code(eg. LHR, AMS)
to	3 letter IATA code(eg. LHR, AMS)
outboundDate	ISO_LOCAL_DATE format
inboundDate	ISO_LOCAL_DATE format
numberOfAdults	Number of passengers
Response

Name	Description
carrier	Name of the Airline
basePrice	Price without tax(doesn't include discount)
tax	Tax which needs to be charged along with the price
discount	Discount which needs to be applied on the price(in percentage)
departureAirportName	3 letter IATA code(eg. LHR, AMS)
arrivalAirportName	3 letter IATA code(eg. LHR, AMS)
outboundDateTime	ISO_INSTANT format
inboundDateTime	ISO_INSTANT format*/

// TODO implement this supplier
public class ToughJet implements FlightSupplier {
    @Override
    public Optional<Flight> findBy(SearchFlightRequest flightRequest) {
        return Optional.empty();
    }
}
