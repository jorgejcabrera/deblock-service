package org.deblock.exercise.infrastructure.crazyair;


import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.deblock.exercise.domain.SearchFlightRequest;
import org.deblock.exercise.domain.Flight;
import org.deblock.exercise.domain.FlightSupplier;
import org.deblock.exercise.infrastructure.NetworkConnectionConfig;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Optional;

public class CrazyAir implements FlightSupplier {
    private final ObjectMapper objectMapper;
    private final OkHttpClient client;
    private final String baseUrl;

    public CrazyAir(NetworkConnectionConfig config, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.baseUrl = config.getTargetHost();
        this.client = new OkHttpClient().newBuilder()
                .readTimeout(config.getReadTo())
                .writeTimeout(config.getWriteTo())
                .connectTimeout(config.getConnectionTo())
                .build();
    }

    @Override
    public Optional<Flight> findBy(SearchFlightRequest flightRequest) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .get()
                .url(String.format(baseUrl + "/flights?" +
                        "origin=" + flightRequest.getOrigin() +
                        "&destination=" + flightRequest.getDestination() +
                        "&departureDate=" + flightRequest.getDepartureDate() +
                        "&returnDate=" + flightRequest.getReturnDate() +
                        "&passengerCount=" + flightRequest.getNumberOfPassengers()))
                .addHeader("Authorization", "Bearer authorizationheader")
                .build();

        Call networkCall = client.newCall(request);
        Optional<Flight> response;
        try {
            Response cazyAirResponse = networkCall.execute();
            response = buildResponse(cazyAirResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @NotNull
    private Optional<Flight> buildResponse(Response apiResponse) throws IOException {
        if (!apiResponse.isSuccessful()) {
            return Optional.empty();
        }
        Optional<CrazyAirResponse> crazyAirResponse = Optional.of(objectMapper.readValue(apiResponse.body().string(), CrazyAirResponse.class));
        return Optional.of(crazyAirResponse.get().toModel());
    }
}
