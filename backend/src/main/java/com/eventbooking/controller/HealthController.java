package com.eventbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", System.currentTimeMillis());
        health.put("service", "event-booking-backend");
        
        try {
            // Test MongoDB connection
            mongoTemplate.getCollection("users").estimatedDocumentCount();
            health.put("database", "Connected");
        } catch (Exception e) {
            health.put("database", "Disconnected: " + e.getMessage());
        }
        
        return ResponseEntity.ok(health);
    }
}