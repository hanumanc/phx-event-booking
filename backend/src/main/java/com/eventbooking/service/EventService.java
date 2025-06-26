package com.eventbooking.service;

import com.eventbooking.entity.Event;
import com.eventbooking.entity.EventStatus;
import com.eventbooking.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByStatus(EventStatus status) {
        return eventRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    public List<Event> getEventsByVendor(String vendorId) {
        return eventRepository.findByVendorId(vendorId);
    }

    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }

    public Event saveEvent(Event event) {
        if (event.getId() == null) {
            event.setCreatedAt(LocalDateTime.now());
        }
        event.setUpdatedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

    public List<Event> searchEvents(String query) {
        return eventRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    public List<Event> getEventsByLocation(String location) {
        return eventRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Event> getApprovedEvents() {
        return eventRepository.findByStatusOrderByCreatedAtDesc(EventStatus.APPROVED);
    }
}