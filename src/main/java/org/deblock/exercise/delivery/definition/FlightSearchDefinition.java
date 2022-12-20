package org.deblock.exercise.delivery.definition;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.deblock.exercise.domain.flight.search.DefaultFlightSearch;
import org.deblock.exercise.domain.flight.search.FlightSearch;
import org.deblock.exercise.domain.flight.FlightSupplier;
import org.deblock.exercise.infrastructure.NetworkConnectionConfig;
import org.deblock.exercise.infrastructure.crazyair.CrazyAir;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

import static java.time.Duration.ofMillis;

@Configuration
public class FlightSearchDefinition {

    @Bean
    NetworkConnectionConfig crazyNetworkConfiguration() {
        return new NetworkConnectionConfig(ofMillis(1000),
                ofMillis(1000),
                ofMillis(2000),
                "https://crazyair.com");
    }

    @Bean
    Set<FlightSupplier> flightSuppliers(NetworkConnectionConfig crazyNetworkConfiguration, ObjectMapper mapper) {
        FlightSupplier crazyAir = new CrazyAir(crazyNetworkConfiguration, mapper);
        return Set.of(crazyAir);
    }

    @Bean
    FlightSearch flightSearch(Set<FlightSupplier> flightSuppliers) {
        return new DefaultFlightSearch(flightSuppliers);
    }
}
