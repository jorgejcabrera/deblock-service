package org.deblock.exercise.infrastructure;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.http.HttpStatus;

import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class MockServer {
    private static WireMockServer wireMockServer;

    public static void init() {
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort().notifier(new ConsoleNotifier(true)));
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    public static int port() {
        return wireMockServer.port();
    }

    public static void crazyAirServer() {
        stubFor(get(urlPathMatching("/flights.*"))
                .withHeader("Authorization", WireMock.equalTo("Bearer authorizationheader"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"airline\":\"qatarairways\", \"price\":1200, \"cabinclass\":\"E\", \"departureAirportCode\":\"EZE\", \"destinationAirportCode\":\"IST\", \"departureDate\":\"" + LocalDateTime.now() + "\",\"arrivalDate\":\"" + LocalDateTime.now().plusDays(30) + "\"}")
                )
        );
    }

    public static void emptyCrazyAirServer() {
        stubFor(get(urlPathMatching("/flights.*"))
                .withHeader("Authorization", WireMock.equalTo("Bearer authorizationheader"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody("{\"message\":\"There isn't any flight available\"}")
                )
        );
    }

    public static void brokenCrazyAirServer() {
        stubFor(get(urlPathMatching("/flights.*"))
                .withHeader("Authorization", WireMock.equalTo("Bearer authorizationheader"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withFixedDelay(2000)
                        .withBody("{\"airline\":\"qatarairways\", \"price\":1200, \"cabinclass\":\"E\", \"departureAirportCode\":\"EZE\", \"destinationAirportCode\":\"IST\", \"departureDate\":\"" + LocalDateTime.now() + "\",\"arrivalDate\":\"" + LocalDateTime.now().plusDays(30) + "\"}")
                )
        );
    }

    public static void resetRequest() {
        wireMockServer.resetRequests();
    }
}
