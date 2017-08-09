package com.tiyhackathon.meetup.domain;

/**
 * Created by David Turk on 8/9/17.
 */
public class Attendee {
    private int id;
    private User user;
    private Event event;

    public Attendee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendee attendee = (Attendee) o;

        return id == attendee.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "id=" + id +
                ", user=" + user +
                ", event=" + event +
                '}';
    }
}
