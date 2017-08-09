package com.tiyhackathon.meetup.service;

import com.tiyhackathon.meetup.domain.Event;
import com.tiyhackathon.meetup.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by David Turk on 8/9/17.
 */
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event addEvent(Event newEvent) {
        return eventRepository.save(newEvent);
    }

    @Override
    public Event getEventById(int id) {
        return eventRepository.findOne(id);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }
}
