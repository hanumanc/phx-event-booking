package com.eventbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SimpleHealthController {

    @GetMapping("/simple-health")
    public ResponseEntity<Map<String, Object>> simpleHealth() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", System.currentTimeMillis());
        health.put("service", "event-booking-backend");
        health.put("message", "Framework is working correctly!");
        
        return ResponseEntity.ok(health);
    }
}