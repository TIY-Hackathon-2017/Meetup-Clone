package com.tiyhackathon.meetup.domain;

/**
 * Created by David Turk on 8/8/17.
 */
public class Note {
    private int id;
    private User author;
    private User about;
    private String body;

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAbout() {
        return about;
    }

    public void setAbout(User about) {
        this.about = about;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        return id == note.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", author=" + author +
                ", about=" + about +
                ", body='" + body + '\'' +
                '}';
    }
}
