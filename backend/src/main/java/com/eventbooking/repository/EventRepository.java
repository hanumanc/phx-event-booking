package com.eventbooking.repository;

import com.eventbooking.entity.Event;
import com.eventbooking.entity.EventStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    
    List<Event> findByVendorId(String vendorId);
    
    List<Event> findByStatus(EventStatus status);
    
    List<Event> findByStatusOrderByCreatedAtDesc(EventStatus status);
    
    List<Event> findByCategory(String category);
    
    List<Event> findByLocationContainingIgnoreCase(String location);
    
    List<Event> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    
}