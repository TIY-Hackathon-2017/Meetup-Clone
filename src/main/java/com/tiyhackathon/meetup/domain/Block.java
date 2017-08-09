package com.tiyhackathon.meetup.domain;

/**
 * Created by David Turk on 8/9/17.
 */
public class Block {
    private int id;
    private User user;
    private User userBeingBlocked;

    public Block() {
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

    public User getUserBeingBlocked() {
        return userBeingBlocked;
    }

    public void setUserBeingBlocked(User userBeingBlocked) {
        this.userBeingBlocked = userBeingBlocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        return id == block.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", user=" + user +
                ", userBeingBlocked=" + userBeingBlocked +
                '}';
    }
}
