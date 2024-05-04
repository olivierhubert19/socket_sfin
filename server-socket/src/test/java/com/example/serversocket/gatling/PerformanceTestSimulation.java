package com.example.serversocket.gatling;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PerformanceTestSimulation extends Simulation {

    private static final HttpProtocolBuilder HTTP_PROTOCOL_BUILDER = setupProtocolForSimulation();

    private static final ScenarioBuilder GET_SCENARIO_BUILDER = buildGetScenario();

    public PerformanceTestSimulation() {
        setUp(GET_SCENARIO_BUILDER.injectOpen(getEndpointInjectionProfile())
                .protocols(HTTP_PROTOCOL_BUILDER))
                .assertions(global().responseTime().max().lte(10000),
                        global().successfulRequests().percent().gt(90d));
    }

    private static OpenInjectionStep getEndpointInjectionProfile() {
        int totalDesiredUserCount = 200;
        double userRampUpPerSecond = 50;
        double rampUpIntervalSeconds = 30;
        int totalRampUptimeSeconds = 120;
        int steadyStateDurationSeconds = 300;

        return rampUsersPerSec(userRampUpPerSecond)
                .to(totalDesiredUserCount)
                .during(Duration.ofSeconds(totalRampUptimeSeconds + steadyStateDurationSeconds));
    }

    private static HttpProtocolBuilder setupProtocolForSimulation() {
        return http.baseUrl("http://localhost:9001")
                .acceptHeader("application/json")
                .maxConnectionsPerHost(10)
                .userAgentHeader("Gatling/Performance Test");
    }

    private static ScenarioBuilder buildGetScenario() {
        return CoreDsl.scenario("Load Test for Performance API")
                .exec(http("get-performance-request")
                        .get("/performance/test_chat_message")
                        .check(status().is(200)));
    }
}

