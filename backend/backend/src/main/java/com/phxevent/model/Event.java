package com.phxevent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "events") // This maps to MongoDB collection 'events'
public class Event {

    @Id
    private String id;

    private String name;
    private String location;

    public Event() {}

    public Event(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}
