package com.stockwatch.capstone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    /**
     * Api to check health of server.
     *
     * @return
     */
    @GetMapping("/health")
    public boolean getHealth() {
        return true;
    }
}
