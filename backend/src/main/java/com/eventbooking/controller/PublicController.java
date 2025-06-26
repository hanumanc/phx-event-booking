package com.eventbooking.controller;

import com.eventbooking.entity.Event;
import com.eventbooking.entity.EventStatus;
import com.eventbooking.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
@Tag(name = "Public Events", description = "Public event discovery APIs")
public class PublicController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    @Operation(summary = "Get all approved events", description = "Retrieve all approved public events")
    public ResponseEntity<List<Event>> getAllApprovedEvents() {
        List<Event> events = eventService.getApprovedEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/{id}")
    @Operation(summary = "Get event details", description = "Get detailed information about a specific event")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent() && event.get().getStatus() == EventStatus.APPROVED) {
            return ResponseEntity.ok(event.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/events/search")
    @Operation(summary = "Search events", description = "Search events by title or description")
    public ResponseEntity<List<Event>> searchEvents(@RequestParam String query) {
        List<Event> events = eventService.searchEvents(query);
        // Filter only approved events for public access
        List<Event> approvedEvents = events.stream()
                .filter(event -> event.getStatus() == EventStatus.APPROVED)
                .toList();
        return ResponseEntity.ok(approvedEvents);
    }

    @GetMapping("/events/location")
    @Operation(summary = "Get events by location", description = "Get events filtered by location")
    public ResponseEntity<List<Event>> getEventsByLocation(@RequestParam String location) {
        List<Event> events = eventService.getEventsByLocation(location);
        // Filter only approved events for public access
        List<Event> approvedEvents = events.stream()
                .filter(event -> event.getStatus() == EventStatus.APPROVED)
                .toList();
        return ResponseEntity.ok(approvedEvents);
    }
}