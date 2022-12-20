package org.deblock.exercise.delivery.rest;

import org.deblock.exercise.delivery.definition.FlightSearchDefinition;
import org.deblock.exercise.delivery.handler.ExceptionHandlerAdvice;
import org.deblock.exercise.domain.flight.Flight;
import org.deblock.exercise.domain.flight.search.FlightSearch;
import org.deblock.exercise.domain.flight.FlightSupplier;
import org.deblock.exercise.infrastructure.NetworkConnectionConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {SearchFlightController.class})
@AutoConfigureMockMvc
@ContextConfiguration(classes = {SearchFlightController.class, FlightSearchDefinition.class})
class SearchFlightControllerTest {

    @MockBean
    private NetworkConnectionConfig crazyNetworkConfiguration;
    @MockBean
    private Set<FlightSupplier> flightSuppliers;
    @MockBean
    private FlightSearch flightSearch;

    private MockMvc mockMvc;

    @Autowired
    private SearchFlightController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ExceptionHandlerAdvice handler = new ExceptionHandlerAdvice();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(handler)
                .build();
    }

    @Test
    void testWhenExistsAtLeastOneFlightThenItMustBeRetrieved() throws Exception {
        // GIVEN
        givenAFlightRetrieved();

        // WHEN
        String response = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/flights")
                        .param("origin", "eze")
                        .param("destination", "ist")
                        .param("departureDate", LocalDateTime.now().toString())
                        .param("returnDate", LocalDateTime.now().plusDays(10).toString())
                        .param("numberOfPassengers", "2")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // THEN
        thenAtLeastAFlightWasRetrieved(response);
    }

    @Test
    void testWhenAFieldWereMissedThenABadRequestMustBeRetrieved() throws Exception {
        // WHEN
        String response = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/flights")
                        .param("destination", "ist")
                        .param("departureDate", LocalDateTime.now().toString())
                        .param("returnDate", LocalDateTime.now().plusDays(10).toString())
                        .param("numberOfPassengers", "2")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        // THEN
        assertEquals("{\"errorCode\":\"invalid_fields\",\"message\":\"Origin, destination, departureDate, returnDate and numberOfPassengers are mandatory\"}", response);
    }

    @Test
    void testWhenTheAmountOfPassengersIsWrongThenABadRequestMustBeRetrieved() throws Exception {
        // WHEN
        String response = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/flights")
                        .param("origin", "eze")
                        .param("destination", "ist")
                        .param("departureDate", LocalDateTime.now().toString())
                        .param("returnDate", LocalDateTime.now().plusDays(10).toString())
                        .param("numberOfPassengers", "56")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        // THEN
        assertEquals("{\"errorCode\":\"invalid_amount_of_passengers\",\"message\":\"Maximum number of passenger are 4\"}", response);
    }

    @Test
    void testWhenTheOriginIsWrongThenABadRequestMustBeRetrieved() throws Exception {
        // WHEN
        String response = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/flights")
                        .param("origin", "ezeiza")
                        .param("destination", "ist")
                        .param("departureDate", LocalDateTime.now().toString())
                        .param("returnDate", LocalDateTime.now().plusDays(10).toString())
                        .param("numberOfPassengers", "2")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        // THEN
        assertEquals("{\"errorCode\":\"invalid_airport_code\",\"message\":\"Invalid origin airport code\"}", response);
    }

    private static void thenAtLeastAFlightWasRetrieved(String response) {
        assertNotNull(response);
    }

    private void givenAFlightRetrieved() {
        when(flightSearch.findAllBy(any())).thenReturn(Set.of(new Flight(
                "airline",
                "supplier",
                new BigDecimal(9),
                "EZE",
                "IST",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30))));
    }
}
