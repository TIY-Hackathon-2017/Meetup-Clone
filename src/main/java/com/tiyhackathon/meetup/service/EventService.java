package com.tiyhackathon.meetup.service;

import com.tiyhackathon.meetup.domain.Event;

/**
 * Created by David Turk on 8/9/17.
 */
public interface EventService {
    public Event addEvent(Event newEvent);
    public Event getEventById(int id);
    public Event updateEvent(Event event);
    public void deleteEvent(Event event);
}
