package org.deblock.exercise.infrastructure;

import java.time.Duration;

public class NetworkConnectionConfig {
    private String targetHost;
    private Duration readTo;
    private Duration writeTo;
    private Duration connectionTo;

    public NetworkConnectionConfig(Duration readTo,
                                   Duration writeTo,
                                   Duration connectionTo,
                                   String targetHost) {
        this.readTo = readTo;
        this.writeTo = writeTo;
        this.connectionTo = connectionTo;
        this.targetHost = targetHost;
    }

    public String getTargetHost() {
        return targetHost;
    }

    public Duration getReadTo() {
        return readTo;
    }

    public Duration getWriteTo() {
        return writeTo;
    }

    public Duration getConnectionTo() {
        return connectionTo;
    }
}
