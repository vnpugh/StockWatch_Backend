package com.stockwatch.capstone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    /**
     * https://www.baeldung.com/spring-boot-health-indicators
     * Health check endpoint to check the health of the server.
     * This endpoint returns a boolean value indicating the health of the server.
     * A `true` value indicates that the server is healthy and functioning properly.
     * @return `true` if the server is healthy, `false` otherwise
     */
    @GetMapping("/health")
    public boolean getHealth() {
        return true;
    }
}