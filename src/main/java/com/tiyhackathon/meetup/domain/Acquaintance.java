package com.tiyhackathon.meetup.domain;

/**
 * Created by David Turk on 8/9/17.
 */
public class Acquaintance {
    private int id;
    private User userId;
    private User linkedUser;

    public Acquaintance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public User getLinkedUser() {
        return linkedUser;
    }

    public void setLinkedUser(User linkedUser) {
        this.linkedUser = linkedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Acquaintance that = (Acquaintance) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Acquaintance{" +
                "id=" + id +
                ", userId=" + userId +
                ", linkedUser=" + linkedUser +
                '}';
    }
}
